package com.range.adb.reboot

import com.range.common.util.WrapperUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AdbRebootServiceImpl(
    private val wrapperUtil: WrapperUtil
) : AdbRebootService {
    override fun reboot(deviceId: String): Flux<String> {
       return  wrapperUtil.getAdbOutputFlux(deviceId,"reboot")
    }

    override fun rebootFastboot(deviceId: String): Flux<String> {
      return wrapperUtil.getAdbOutputFlux(deviceId,"reboot bootloader")
    }

    override fun rebootFastbootD(deviceId: String): Flux<String> {
        return wrapperUtil.getAdbOutputFlux(deviceId,"reboot fastboot")
    }

    override fun rebootRecovery(deviceId: String): Flux<String> {
        return wrapperUtil.getAdbOutputFlux(deviceId,"reboot recovery")
    }
}