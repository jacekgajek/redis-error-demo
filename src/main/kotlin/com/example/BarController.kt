package com.example

import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import mu.KotlinLogging
import java.time.LocalDateTime
import kotlin.reflect.KClass

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
