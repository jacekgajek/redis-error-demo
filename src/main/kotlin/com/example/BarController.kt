package com.example

import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.Cacheable
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import kotlinx.coroutines.delay
import java.io.Serializable
import java.time.LocalDateTime

@Controller
@CacheConfig("default")
open class BarController {

    data class Bar(val bar: String) : Serializable

    private val log = logger(BarController::class)

    @Get(uri = "/bar")
    @Cacheable
    open fun bar2(): Bar {
        log.info { "Bar blocking function was called" }
        return Bar(LocalDateTime.now().toString())
    }

    @Get(uri = "/bar-suspend")
    @Cacheable
    open suspend fun bar(): Bar {
        log.info { "Bar suspending function was called" }
        delay(5000)
        return Bar(LocalDateTime.now().toString())
    }
}
