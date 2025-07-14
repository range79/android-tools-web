package com.range.fastboot.api

import com.range.fastboot.domain.model.DeviceInfo
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/device/write")
interface ReadFastbootApi{
    fun getDeviceInfo(): DeviceInfo
    fun getBootloaderStatus(): Boolean
    fun getDeviceName(): String
}
