package com.example

import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import kotlinx.coroutines.delay
import mu.KotlinLogging
import java.io.Serializable
import java.time.LocalDateTime
import kotlin.reflect.KClass

@Controller
@CacheConfig("default")
open class FooController {

    data class Foo(val foo: String) : Serializable

    private val log = logger(FooController::class)

    @Get(uri = "/foo")
    @Cacheable
    open fun foo2(param: String): Foo {
        log.info { "Foo blocking function was called with parameter $param" }
        return Foo(param + ": " + LocalDateTime.now().toString())
    }
    @Get(uri = "/foo-suspend")
    @Cacheable
    open suspend fun foo(param: String): Foo {
        log.info { "Foo suspending function was called with parameter $param" }
        delay(5000)
        return Foo(param + ": " + LocalDateTime.now().toString())
    }
}
