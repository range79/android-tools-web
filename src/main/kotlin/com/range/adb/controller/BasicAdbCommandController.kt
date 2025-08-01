package com.range.adb.controller

import com.range.adb.api.BasicAdbCommandApi
import com.range.adb.dto.AdbDeviceResponseDto
import com.range.adb.service.reboot.BasicAdbCommandsService
import com.range.common.enums.RebootOptions
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicAdbCommandController(
    private val basicCommandsService: BasicAdbCommandsService
): BasicAdbCommandApi {
    override fun reboot(
        id: Long,
        option: RebootOptions
    ): ResponseEntity<AdbDeviceResponseDto> {
       return ResponseEntity.ok(basicCommandsService.reboot(id,option))
    }
}