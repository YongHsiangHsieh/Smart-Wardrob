plugins {
    kotlin("jvm") version "1.9.20"
    // Plugin for Dokka - KDoc generating tool
    id("org.jetbrains.dokka") version "1.9.10"
    // Code coverage tool
    jacoco
    // Plugin for Ktlint
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    application
    kotlin("plugin.serialization") version "1.5.0"
}

group = "me.YongHsiangHsieh"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
    testImplementation("org.testng:testng:7.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    // For generating a Dokka Site from KDoc
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.10")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    implementation("org.yaml:snakeyaml:1.29")
}

tasks.test {
    useJUnitPlatform()
    // report is always generated after tests run
    finalizedBy(tasks.jacocoTestReport)
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "MainKt"
    // for building a fat jar - include all dependencies
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
