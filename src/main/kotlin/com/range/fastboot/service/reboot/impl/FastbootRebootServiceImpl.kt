package com.range.fastboot.service.reboot.impl

import com.range.fastboot.service.reboot.FastbootRebootService
import com.range.fastboot.util.WrapperUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class FastbootRebootServiceImpl(
    private val wrapperUtil: WrapperUtil
) : FastbootRebootService {
    override fun reboot(deviceId: String): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(deviceId,"reboot")

    }

    override fun rebootFastboot(deviceId: String): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(deviceId,"reboot bootloader")
    }

    override fun rebootFastbootD(deviceId: String): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(deviceId,"reboot fastboot")
    }

    override fun rebootRecovery(deviceId: String): Flux<String> {
        return wrapperUtil.getFastbootOutputFlux(deviceId,"reboot recovery")
    }


}
