package com.range.fastboot.api

import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/device/write")
interface WriteFastbootApi {

    fun writeSystem()
    fun writeVendor()
    fun writeProduct()
    fun writeCast()
    fun writePreloader()
}