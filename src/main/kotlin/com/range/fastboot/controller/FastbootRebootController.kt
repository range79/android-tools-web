package com.range.fastboot.controller

import com.range.fastboot.api.FastbootRebootApi
import com.range.fastboot.service.FastbootRebootService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class FastbootRebootController(
    private val fastbootRebootService: FastbootRebootService
): FastbootRebootApi {

    override fun reboot(): Flux<String> {
        return fastbootRebootService.reboot()
    }

    override fun rebootFastboot() : Flux<String>{
        return fastbootRebootService.rebootFastboot()
    }

    override fun rebootFastbootD(): Flux<String> {
        return fastbootRebootService.rebootFastbootD()
    }

    override fun rebootRecovery(): Flux<String> {
        return fastbootRebootService.rebootRecovery()
    }


}
