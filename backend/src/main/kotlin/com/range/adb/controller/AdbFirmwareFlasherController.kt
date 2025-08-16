package com.range.adb.controller

import com.range.adb.api.AdbFirmwareFlasherApi
import com.range.adb.dto.AdbDeviceResponseDto
import com.range.adb.service.flasher.AdbFirmwareFlasher
import org.springframework.web.bind.annotation.RestController

@RestController
class AdbFirmwareFlasherController(
    private val adbFirmwareFlasher: AdbFirmwareFlasher
): AdbFirmwareFlasherApi {
    override fun install(firmwareId: Long, deviceId: Long): AdbDeviceResponseDto {
        return adbFirmwareFlasher.install(deviceId, firmwareId)
    }
}