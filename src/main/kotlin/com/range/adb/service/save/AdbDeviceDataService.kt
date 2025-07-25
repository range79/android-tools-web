package com.range.adb.service.save

import com.range.adb.dto.AdbDeviceResponseDto

interface AdbDeviceDataService {
    fun scanDevices(): List<String>
    fun getCodename(serial: String): String?
    fun getAndroidVersion(serial: String): String
    fun saveDevice(serial: String): AdbDeviceResponseDto
}