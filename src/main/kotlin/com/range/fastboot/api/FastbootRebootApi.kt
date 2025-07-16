package com.range.fastboot.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Flux
@RequestMapping("/fastboot/{id}/reboot")
interface FastbootRebootApi {
    @GetMapping()
    fun reboot(@PathVariable id : String): Flux<String>
    @GetMapping("/fastboot")
    fun rebootFastboot(@PathVariable id : String): Flux<String>
    @GetMapping("/fastbootd")
    fun rebootFastbootD(@PathVariable id : String):Flux<String>
    @GetMapping("/recovery")
    fun rebootRecovery(@PathVariable id : String):Flux<String>
}