package com.range.fastboot.service.save.impl

import com.range.common.util.WrapperUtil
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.service.save.FastbootDeviceDataService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.io.println

@Service
class FastbootDeviceDataServiceImpl(
    private val wrapperUtil: WrapperUtil,
    private val fastbootDevicesInfoRepository: FastbootDevicesInfoRepository
): FastbootDeviceDataService {
    override fun scanDevices(): List<String> {
        val output = wrapperUtil.getFastbootOutput(null, "devices")

        println(output)
        return output.lines()
            .filter { it.isNotEmpty() && it != "List of devices attached" }
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

    override fun getDeviceCodeName(deviceId: String): String? {
        val output = wrapperUtil.getFastbootOutput(deviceId,"getvar product")
        return Regex("product:\\s*(\\S+)")
            .find(output)
            ?.groupValues?.get(1)
    }


    @Transactional
    override fun saveDevice(deviceId: String): FastbootDeviceResponseDto {
        val codename = getDeviceCodeName(deviceId)
        val isAbdDevice = getIsAbdDevice(deviceId)
        val getUnlockStatus = getUnlockStatus(deviceId)

        val deviceObject = FastbootDeviceInfo(
            id = null,
            serial= deviceId,
            codename = codename,
            unlocked = getUnlockStatus,
            isAbDevice =isAbdDevice,
            fastbootDeviceStatus = FastbootDeviceStatus.Connected,
        )
        fastbootDevicesInfoRepository.save(deviceObject)
        return FastbootDeviceResponseDto(deviceObject.serial,deviceObject.fastbootDeviceStatus)

    }




}