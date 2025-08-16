package com.range.fastboot.service.devices.impl

import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import com.range.fastboot.service.devices.DeviceService
import com.range.fastboot.util.CheckFastbootDevices
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class DeviceServiceImpl(
    private val fastbootRepository: FastbootDevicesInfoRepository,
    private val checkFastbootDevices: CheckFastbootDevices
): DeviceService {
    @Transactional
    override fun getAllDevices(pageable: Pageable): Page<FastbootDeviceInfo> {
        val  fastbootDevices =fastbootRepository.findAll(pageable)
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
        val currentStatus = checkFastbootDevices.checkNow(fastbootDevice.serial)
        if (fastbootDevice.fastbootDeviceStatus != currentStatus) {
            fastbootDevice.fastbootDeviceStatus = currentStatus
            fastbootRepository.save(fastbootDevice)
        }
        return fastbootDevice
    }
}