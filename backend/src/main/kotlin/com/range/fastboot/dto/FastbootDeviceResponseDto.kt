package com.range.fastboot.dto

import com.range.fastboot.enums.FastbootDeviceStatus

data class FastbootDeviceResponseDto (
    val serial: String,
    val fastbootDeviceStatus: FastbootDeviceStatus
)