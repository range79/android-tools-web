package com.range.fastboot.controller

import com.range.common.enums.RebootOptions
import com.range.fastboot.api.FastbootRebootDeviceApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.service.reboot.FastbootRebootService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
@RestController

class FastbootRebootController(
    private val fastbootRebootService: FastbootRebootService
): FastbootRebootDeviceApi {
    override fun reboot(id: Long,option: RebootOptions):ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(fastbootRebootService.reboot(id,option))
    }


}
