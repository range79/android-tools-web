package com.range.fastboot.service.impl

import com.range.fastboot.service.FastbootRebootService
import com.range.fastboot.util.WrapperUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class FastbootRebootServiceImpl(
    private val wrapperUtil: WrapperUtil
) : FastbootRebootService {
    override fun reboot(): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(" reboot")

    }

    override fun rebootFastboot(): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(" reboot bootloader")
    }

    override fun rebootFastbootD(): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(" reboot fastboot")
    }

    override fun rebootRecovery(): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(" reboot recovery")
    }


}
