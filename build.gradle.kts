plugins {
    kotlin("jvm") version "2.3.20-Beta2"
    id("com.gradleup.shadow") version "8.3.0"
}

group = "me.djdisaster"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks {
    shadowJar {
        archiveBaseName.set("Portfolio")
        archiveClassifier.set("")
        archiveVersion.set("")
        manifest {
            attributes["Main-Class"] = "me.djdisaster.MainKt"
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
