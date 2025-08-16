package com.range.adb.controller.data

import com.range.adb.api.data.AdbDevicesFetcherApi
import com.range.adb.domain.entity.AdbDevice
import com.range.adb.service.devices.AdbDeviceService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class AdbDeviceFetcherController(
    private val service: AdbDeviceService,
): AdbDevicesFetcherApi {
    override fun getAllSavedDevices(pageable: Pageable): Page<AdbDevice> {
        return service.getAllDevices(pageable)
    }

    override fun getDevice(id: Long): AdbDevice {
        return service.getOneDevice(id)
    }
}