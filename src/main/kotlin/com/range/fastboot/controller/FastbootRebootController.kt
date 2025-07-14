package com.range.fastboot.controller

import com.range.fastboot.api.FastbootRebootApi
import com.range.fastboot.service.FastbootRebootService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class FastbootRebootController(
    private val fastbootRebootService: FastbootRebootService
): FastbootRebootApi {
    @GetMapping("/reboot")
    override fun reboot(): Flux<String> {
        return fastbootRebootService.reboot()
    }

    override fun rebootFastboot() {
        TODO("Not yet implemented")
    }

    override fun rebootFastbootD() {
        TODO("Not yet implemented")
    }

    override fun rebootRecovery() {
        TODO("Not yet implemented")
    }

}
