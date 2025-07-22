package com.range.fastboot.controller.data

import com.range.fastboot.api.data.RefreshDevicesApi
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.service.devices.DeviceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshDevicesController(
    private val service: DeviceService
): RefreshDevicesApi {
    override fun getAllSavedDevices(): ResponseEntity<List<FastbootDeviceInfo>> {
        return ResponseEntity.ok(service.getAllDevices())
    }

    override fun getDevice(id: Long): ResponseEntity<FastbootDeviceInfo> {
      return ResponseEntity.ok(service.getOneDevice(id))
    }
}