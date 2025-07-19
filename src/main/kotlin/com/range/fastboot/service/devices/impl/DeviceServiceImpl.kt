package com.range.fastboot.service.devices.impl

import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import com.range.fastboot.service.devices.DeviceService
import com.range.fastboot.util.CheckDevices
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class DeviceServiceImpl(
    private val fastbootRepository: FastbootDevicesInfoRepository,
    private val checkDevices: CheckDevices
): DeviceService {
    @Transactional
    override fun getAllDevices(): List<FastbootDeviceInfo> {
        val  fastbootDevices =fastbootRepository.findAll()
        val updatedDevices = fastbootDevices.map { device ->

            fastbootChecker(device)

        }
        return updatedDevices
    }
    @Transactional
    override fun getOneDevice(id: Long): FastbootDeviceInfo {
        val fastbootDevice = fastbootRepository.findById(id).orElseThrow{
            FastbootDeviceNotFoundException("Device with id $id Not found")
        }
        return fastbootChecker(fastbootDevice)
    }

    private fun fastbootChecker(fastbootDevice: FastbootDeviceInfo): FastbootDeviceInfo {
        val currentStatus = checkDevices.checkNow(fastbootDevice.serial)
        if (fastbootDevice.fastbootDeviceStatus != currentStatus) {
            fastbootDevice.fastbootDeviceStatus = currentStatus
            fastbootRepository.save(fastbootDevice)
        }
        return fastbootDevice
    }
}