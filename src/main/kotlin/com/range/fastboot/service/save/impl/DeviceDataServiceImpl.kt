package com.range.fastboot.service.save.impl

import com.range.fastboot.domain.repository.DeviceInfoRepository
import com.range.fastboot.service.save.DeviceDataService
import com.range.fastboot.util.WrapperUtil
import org.springframework.stereotype.Service
import kotlin.collections.map
import kotlin.text.substringBefore

@Service
class DeviceDataServiceImpl(
    private val wrapperUtil: WrapperUtil,
//    private val deviceInfoRepository: DeviceInfoRepository
): DeviceDataService {
    override fun getDevices(): List<String> {
        val output = wrapperUtil.getFastbootOutput(null, "devices")
        return output.lines()
            .filter { it.contains("fastboot") }
            .map { it.substringBefore("\t").trim() }
    }

    override fun getUnlockStatus(deviceId: String) : Boolean{
        val oemLockStatus = wrapperUtil.getFastbootOutput(deviceId,"getvar unlocked" )
        if (oemLockStatus.contains("yes")) {
            return true
        }
        else if (oemLockStatus.contains("no")) {
            return false
        }
        else
            return false
    }

    override fun getIsAbdDevice(deviceId: String): Boolean {
        val output = wrapperUtil.getFastbootOutput(deviceId,"getvar has-slot:boot")
        if (output.contains("yes")) {
            return true
        }
        else if (output.contains("no")) {
            return false
        }
        else
            return false
    }

    override fun getDeviceCodeName(deviceId: String): String {
        val output = wrapperUtil.getFastbootOutput(deviceId,"getvar product")

    }


}