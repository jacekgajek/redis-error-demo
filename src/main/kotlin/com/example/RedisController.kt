package com.example

import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import mu.KotlinLogging
import java.time.LocalDateTime
import kotlin.reflect.KClass

fun <T : Any> logger(clazz: KClass<T>?) = KotlinLogging.logger(clazz!!.simpleName!!)

@Controller
@CacheConfig("foo")
open class FooController {

    data class Foo(val foo: String)

    private val log = logger(FooController::class)

    @Get(uri = "/foo")
    @Cacheable
    open fun foo(): Foo {
        log.info { "Fooooooiing" }
        return Foo(LocalDateTime.now().toString())
    }
}

@Controller
@CacheConfig("bar")
open class BarController {

    data class Bar(val bar: String)

    private val log = logger(BarController::class)

    @Get(uri = "/bar")
    @Cacheable
    open fun foo(): Bar {
        log.info { "Barring" }
        return Bar(LocalDateTime.now().toString())
    }
}
