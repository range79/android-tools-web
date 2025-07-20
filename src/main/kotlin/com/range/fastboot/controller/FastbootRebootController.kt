package com.range.fastboot.controller

import com.range.fastboot.api.FastbootRebootApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.service.reboot.FastbootRebootService
import org.springframework.web.bind.annotation.RestController

@RestController
class FastbootRebootController(
    private val fastbootRebootService: FastbootRebootService
): FastbootRebootApi {
    override fun reboot(id: Long): FastbootDeviceResponseDto {
        return fastbootRebootService.reboot(id)
    }

    override fun rebootFastboot(id: Long): FastbootDeviceResponseDto {
        return fastbootRebootService.rebootFastboot(id)
    }

    override fun rebootFastbootD(id: Long): FastbootDeviceResponseDto {
        return    fastbootRebootService.rebootFastbootD(id)
    }

    override fun rebootRecovery(id: Long): FastbootDeviceResponseDto {
        return fastbootRebootService.rebootRecovery(id)
    }


}
