package com.range.fastboot.controller.data

import com.range.fastboot.api.data.FastbootDevicesFetcherApi
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.service.devices.FastbootDeviceService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class FastbootDevicesFetcherController(
    private val service: FastbootDeviceService
): FastbootDevicesFetcherApi {
    override fun getAllSavedDevices(pageable: Pageable): Page<FastbootDeviceInfo> {
        return service.getAllDevices(pageable)
    }



    override fun getDevice(id: Long): FastbootDeviceInfo {
      return service.getOneDevice(id)    }

    override fun getAllSavedDevices(): List<FastbootDeviceInfo> {
        return service.getAllDevicesList()
    }
}