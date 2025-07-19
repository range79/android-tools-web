package com.range.fastboot.service.devices.impl

import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.service.devices.DeviceService
import com.range.fastboot.util.CheckDevices

class DeviceServiceImpl(
    private val fastbootRepository: FastbootDevicesInfoRepository,
    private val checkDevices: CheckDevices
): DeviceService {
    override fun getAllDevices(): List<FastbootDeviceInfo> {
        val  fastbootDevices =fastbootRepository.findAll()
        val updatedDevices = fastbootDevices.map { device ->
            val currentStatus = checkDevices.checkNow(device.serial)
            if (device.fastbootDeviceStatus != currentStatus) {
                device.fastbootDeviceStatus = currentStatus
                fastbootRepository.save(device)
            }
            device
        }
        return updatedDevices
    }

    override fun getOneDevice(id: Long) {
        TODO("Not yet implemented")
    }
}