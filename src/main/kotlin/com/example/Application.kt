package com.example

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*
import mu.KotlinLogging
import kotlin.reflect.KClass

fun <T : Any> logger(clazz: KClass<T>?) = KotlinLogging.logger(clazz!!.simpleName!!)

@OpenAPIDefinition(
    info = Info(
        title = "redis",
        version = "0.0"
    )
)
object Api {
}

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.example")
        .start()
}

