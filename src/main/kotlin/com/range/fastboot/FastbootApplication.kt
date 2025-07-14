package com.range.fastboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FastbootApplication

fun main(args: Array<String>) {
    runApplication<FastbootApplication>(*args)
}
