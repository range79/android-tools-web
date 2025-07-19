package com.range.fastboot.service.save

import com.range.fastboot.dto.DeviceInfoDto

interface DeviceDataService {
    fun getDevices(): List<String>
    fun getUnlockStatus(deviceId: String): Boolean
    fun getIsAbdDevice(deviceId: String): Boolean
    fun getDeviceCodeName(deviceId: String): String?
    fun getFullDevice(deviceId: String): DeviceInfoDto
    fun saveDevice(deviceId: String): DeviceInfoDto
    fun updateDevice(id: Long)
}