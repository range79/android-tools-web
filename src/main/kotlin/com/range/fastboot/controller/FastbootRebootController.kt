package com.range.fastboot.controller

import com.range.fastboot.api.FastbootRebootDeviceApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.service.reboot.FastbootRebootService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class FastbootRebootController(
    private val fastbootRebootService: FastbootRebootService
): FastbootRebootDeviceApi {
    override fun reboot(id: Long):  ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(fastbootRebootService.reboot(id))
    }

    override fun rebootFastboot(id: Long):  ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(fastbootRebootService.rebootFastboot(id))
    }

    override fun rebootFastbootD(id: Long):  ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(fastbootRebootService.rebootFastbootD(id))
    }

    override fun rebootRecovery(id: Long):  ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(fastbootRebootService.rebootRecovery(id))
    }


}
