package com.range.fastboot.service.save

import com.range.fastboot.dto.FastbootDeviceResponseDto

interface FastbootDeviceDataService {
    fun scanDevices(): List<String>
    fun getUnlockStatus(deviceId: String): Boolean
    fun getIsAbdDevice(deviceId: String): Boolean
    fun getDeviceCodeName(deviceId: String): String?
    fun saveDevice(deviceId: String): FastbootDeviceResponseDto

}