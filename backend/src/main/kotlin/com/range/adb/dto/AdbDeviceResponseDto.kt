package com.range.adb.dto

import com.range.adb.enums.AdbDeviceStatus

data class AdbDeviceResponseDto (
    val serial: String,
    val adbDeviceStatus: AdbDeviceStatus
)