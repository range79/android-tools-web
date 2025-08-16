package com.range.adb.controller.data

import com.range.adb.api.data.ADBSaveDeviceApi
import com.range.adb.dto.AdbDeviceResponseDto
import com.range.adb.service.save.AdbDeviceDataService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ADBSaveDeviceController(
    private val  service: AdbDeviceDataService
): ADBSaveDeviceApi {
    override fun scanDevices(): ResponseEntity<List<String>> {
        return ResponseEntity.ok( service.scanDevices())
    }

    override fun saveDevice(id: String): ResponseEntity<AdbDeviceResponseDto> {
        return ResponseEntity.ok( service.saveDevice(id))
    }
}