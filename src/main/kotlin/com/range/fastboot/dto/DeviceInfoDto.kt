package com.range.fastboot.dto

import com.range.fastboot.enums.DeviceStatus

data class DeviceInfoDto (
    var serial: String? = null,
    var codename: String? = null,
    var unlocked: Boolean = false,
    var isAbDevice: Boolean = false,
    var deviceStatus: DeviceStatus? = null
)