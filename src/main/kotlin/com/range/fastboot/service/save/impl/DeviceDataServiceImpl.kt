package com.range.fastboot.service.save.impl

import com.range.common.util.WrapperUtil
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.dto.DeviceInfoDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.service.save.DeviceDataService

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceDataServiceImpl(
    private val wrapperUtil: WrapperUtil,
    private val fastbootDevicesInfoRepository: FastbootDevicesInfoRepository
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

    override fun getDeviceCodeName(deviceId: String): String? {
        val output = wrapperUtil.getFastbootOutput(deviceId,"getvar product")
        return Regex("product:\\s*(\\S+)")
            .find(output)
            ?.groupValues?.get(1)
    }

    override fun getFullDevice(deviceId: String): DeviceInfoDto {
        val codename = getDeviceCodeName(deviceId)
        val isAbdDevice = getIsAbdDevice(deviceId)
        val getUnlockStatus = getUnlockStatus(deviceId)
        val isConnected = FastbootDeviceStatus.Connected
        return DeviceInfoDto(deviceId, codename, getUnlockStatus,isAbdDevice, isConnected)
    }
    @Transactional
    override fun saveDevice(deviceId: String): DeviceInfoDto {
        val device = getFullDevice(deviceId);
        val deviceObject = FastbootDeviceInfo(
            id = null,
            serial= device.serial,
            codename = device.codename,
            unlocked = device.unlocked,
            isAbDevice = device.isAbDevice,
            fastbootDeviceStatus = FastbootDeviceStatus.Connected,
        )
        fastbootDevicesInfoRepository.save(deviceObject)
        return device

    }




}