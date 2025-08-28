package com.range.adb.controller

import com.range.adb.api.AdbFirmwareFlasherApi
import com.range.adb.dto.AdbDeviceResponseDto
import com.range.adb.service.flasher.AdbFirmwareFlasher
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class AdbFirmwareFlasherController(
    private val adbFirmwareFlasher: AdbFirmwareFlasher
): AdbFirmwareFlasherApi {
    override fun install(firmwareId: UUID, deviceId: Long): AdbDeviceResponseDto {
        return adbFirmwareFlasher.install(firmwareId, deviceId)
    }
}