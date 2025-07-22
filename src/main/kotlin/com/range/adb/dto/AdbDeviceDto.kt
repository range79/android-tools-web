package com.range.adb.dto

import com.range.adb.enums.AdbDeviceStatus

data class AdbDeviceDto (
    val serial: String,
    val adbDeviceStatus: AdbDeviceStatus
)