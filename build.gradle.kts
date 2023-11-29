plugins {
    kotlin("jvm") version "1.9.20"
    // Plugin for Dokka - KDoc generating tool
    id("org.jetbrains.dokka") version "1.9.10"
    application
    kotlin("plugin.serialization") version "1.5.0"
}

group = "me.YongHsiangHsieh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    testImplementation("org.testng:testng:7.1.0")
//    implementation("com.squareup.okhttp3:okhttp:4.9.0")

//    implementation("org.yaml:snakeyaml:1.33")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}