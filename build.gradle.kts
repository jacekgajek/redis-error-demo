plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    id("org.jetbrains.kotlin.kapt") version "1.6.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("io.micronaut.application") version "3.0.2"
}

version = "0.1"
group = "com.example"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("kotest")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.openapi:micronaut-openapi")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions:3.0.0")
    implementation("io.micronaut.redis:micronaut-redis-lettuce:5.1.0")
    implementation("io.micronaut.cache:micronaut-cache-core:3.0.0")
    implementation("io.micronaut.rxjava2:micronaut-rxjava2:1.1.0")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("io.micronaut.gcp:micronaut-gcp-logging:4.0.0")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.16")
    runtimeOnly("ch.qos.logback:logback-classic")
    compileOnly("org.graalvm.nativeimage:svm")

    implementation("io.micronaut:micronaut-validation")

    implementation("net.logstash.logback:logstash-logback-encoder:7.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.5.2")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    annotationProcessor("io.micronaut.openapi:micronaut-openapi:3.2.0")
    annotationProcessor("io.micronaut:micronaut-management:3.2.0")
}


application {
    mainClass.set("com.example.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    dockerBuild {
//        images = ["${System.env.DOCKER_IMAGE ?: project.name}:$project.version"]
    }

    dockerBuildNative {
//        images = ["${System.env.DOCKER_IMAGE ?: project.name}:$project.version"]
    }
}
