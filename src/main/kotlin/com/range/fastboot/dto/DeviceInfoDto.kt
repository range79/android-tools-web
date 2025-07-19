package com.range.fastboot.dto

import com.range.fastboot.enums.FastbootDeviceStatus

data class DeviceInfoDto (
    var serial: String? = null,
    var codename: String? = null,
    var unlocked: Boolean = false,
    var isAbDevice: Boolean = false,
    var fastbootDeviceStatus: FastbootDeviceStatus? = null
)