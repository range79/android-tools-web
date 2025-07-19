package com.range.fastboot.api

import com.range.fastboot.domain.entity.FastbootDeviceInfo
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/device/write")
interface ReadFastbootApi{
    fun getDeviceInfo(): FastbootDeviceInfo
    fun getBootloaderStatus(): Boolean
    fun getDeviceName(): String
}