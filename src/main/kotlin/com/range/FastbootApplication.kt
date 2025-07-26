package com.range

import com.range.common.enums.RebootOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class FastbootApplication

fun main(args: Array<String>) {

    print(RebootOptions.Fastboot.toLower())


    runApplication<FastbootApplication>(*args)

}
