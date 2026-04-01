package me.djdisaster

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

fun main() {
    val html = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Hello</title>
        </head>
        <body>
            <h1>Hello world</h1>
        </body>
        </html>
    """.trimIndent().toByteArray()

    val server = HttpServer.create(InetSocketAddress("127.0.0.1", 8000), 0)

    server.createContext("/") { exchange ->
        exchange.responseHeaders.add("Content-Type", "text/html; charset=utf-8")
        exchange.sendResponseHeaders(200, html.size.toLong())
        exchange.responseBody.use { it.write(html) }
    }

    server.executor = null
    server.start()
    println("Serving http://127.0.0.1:8000")
}
