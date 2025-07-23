package com.range.adb.controller

import com.range.adb.api.BasicAdbCommandApi
import com.range.adb.dto.AdbDeviceDto
import com.range.adb.service.reboot.AdbBasicCommandsService
import com.range.common.enums.RebootOptions
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicAdbCommandController(
    private val basicCommandsService: AdbBasicCommandsService
): BasicAdbCommandApi {
    override fun reboot(
        id: Long,
        option: RebootOptions
    ): ResponseEntity<AdbDeviceDto> {
       return ResponseEntity.ok(basicCommandsService.reboot(id,option))
    }
}