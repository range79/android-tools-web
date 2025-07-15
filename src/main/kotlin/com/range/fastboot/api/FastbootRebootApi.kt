package com.range.fastboot.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Flux
@RequestMapping("/reboot")
interface FastbootRebootApi {
    @GetMapping()
    fun reboot(): Flux<String>
    @GetMapping("/fastboot")
    fun rebootFastboot(): Flux<String>
    @GetMapping("/fastbootd")
    fun rebootFastbootD():Flux<String>
    @GetMapping("/recovery")
    fun rebootRecovery():Flux<String>
}