package com.range.fastboot.controller

import com.range.fastboot.api.SaveDevicesApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.service.save.DeviceDataService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SaveDevicesController(
    private val service: DeviceDataService,
): SaveDevicesApi {
    override fun scanDevices(): ResponseEntity<List<String>> {
     return ResponseEntity.ok(service.scanDevices())
    }

    override fun saveDevice(id: String): ResponseEntity<FastbootDeviceResponseDto>  {
        return ResponseEntity.ok(service.saveDevice(id))
    }
}