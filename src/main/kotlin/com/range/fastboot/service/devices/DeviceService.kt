package com.range.fastboot.service.devices

interface DeviceService {
    fun getAllDevices()
    fun getOneDevice(id: Long)
}
