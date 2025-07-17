package com.range.fastboot.service.save

interface DeviceDataService {
    fun getDevices(): List<String>
    fun getUnlockStatus(deviceId: String): Boolean
    fun getIsAbdDevice(deviceId: String): Boolean
    fun getDeviceCodeName(deviceId: String): String?
}