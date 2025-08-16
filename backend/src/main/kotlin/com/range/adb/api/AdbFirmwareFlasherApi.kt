package com.range.adb.api

import com.range.adb.dto.AdbDeviceResponseDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/adb")
interface AdbFirmwareFlasherApi {
    @PostMapping("/flash/{deviceId}/flash/{firmwareId}")
    fun install(@PathVariable firmwareId: Long, @PathVariable deviceId: Long): AdbDeviceResponseDto
}