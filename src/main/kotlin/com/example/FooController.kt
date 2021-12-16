package com.example

import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import mu.KotlinLogging
import java.time.LocalDateTime
import kotlin.reflect.KClass

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
