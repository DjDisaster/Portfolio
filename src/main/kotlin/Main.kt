package me.djdisaster

import com.sun.net.httpserver.HttpServer
import java.io.File
import java.net.InetSocketAddress
import java.nio.file.Files

fun main() {
    val file = File(System.getProperty("user.home") + "/code/site/Portfolio/site/index.html")
    val server = HttpServer.create(InetSocketAddress(8000), 0)

    server.createContext("/") { exchange ->
        if (!file.exists() || !file.isFile) {
            exchange.sendResponseHeaders(404, 0)
            exchange.responseBody.use { it.write("404 Not Found".toByteArray()) }
            return@createContext
        }

        val contentType = Files.probeContentType(file.toPath()) ?: "text/html"
        exchange.responseHeaders.add("Content-Type", contentType)
        exchange.sendResponseHeaders(200, file.length())

        file.inputStream().use { input ->
            exchange.responseBody.use { output ->
                input.copyTo(output)
            }
        }
    }

    server.executor = null
    server.start()
    println("Serving http://localhost:8000")
}