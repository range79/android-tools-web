package com.range.fastboot.service.devices

import com.range.fastboot.domain.entity.FastbootDeviceInfo

interface DeviceService {
    fun getAllDevices(): List<FastbootDeviceInfo>
    fun getOneDevice(id: Long): FastbootDeviceInfo
}
