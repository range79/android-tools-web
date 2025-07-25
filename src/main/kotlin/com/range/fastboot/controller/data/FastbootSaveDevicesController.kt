package com.range.fastboot.controller.data

import com.range.fastboot.api.data.FastbootSaveDevicesApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.service.save.FastbootDeviceDataService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class FastbootSaveDevicesController(
    private val service: FastbootDeviceDataService,
): FastbootSaveDevicesApi {
    override fun scanDevices(): ResponseEntity<List<String>> {
     return ResponseEntity.ok(service.scanDevices())
    }

    override fun saveDevice(id: String): ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(service.saveDevice(id))
    }
}