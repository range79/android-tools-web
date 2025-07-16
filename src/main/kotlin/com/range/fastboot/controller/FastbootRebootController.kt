package com.range.fastboot.controller

import com.range.fastboot.api.FastbootRebootApi
import com.range.fastboot.service.reboot.FastbootRebootService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class FastbootRebootController(
    private val fastbootRebootService: FastbootRebootService
): FastbootRebootApi {

    override fun reboot(id: String): Flux<String> {
        return fastbootRebootService.reboot(id)
    }

    override fun rebootFastboot(id: String) : Flux<String>{
        return fastbootRebootService.rebootFastboot(id)
    }

    override fun rebootFastbootD(id: String): Flux<String> {
        return fastbootRebootService.rebootFastbootD(id)
    }

    override fun rebootRecovery(id: String): Flux<String> {
        return fastbootRebootService.rebootRecovery(id)
    }


}
