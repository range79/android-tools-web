package com.range.adb.service.devices.impl

import com.range.adb.domain.entity.AdbDevice
import com.range.adb.domain.repository.AdbDeviceRepository
import com.range.adb.exception.AdbDeviceNotFoundException
import com.range.adb.service.devices.AdbDeviceService
import com.range.adb.util.CheckAdbDevice
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AdbDeviceServiceImpl(
    private val checkAdbDevice: CheckAdbDevice,
    private val adbDeviceRepository: AdbDeviceRepository
): AdbDeviceService {
    override fun getAllDevices(pageable: Pageable): Page<AdbDevice> {
        val  adbDevices =adbDeviceRepository.findAll(pageable)
        val updatedDevices = adbDevices.map { device ->
            checkAdbDevice(device)
        }
        return updatedDevices
    }


    override fun getOneDevice(id: Long): AdbDevice {
        val adb_device =adbDeviceRepository
            .findById(id)
            .orElseThrow{AdbDeviceNotFoundException("Device with id $id Not found")}
        return checkAdbDevice(adb_device)

    }
    private fun checkAdbDevice(adbDevice: AdbDevice): AdbDevice {
        val currentStatus = checkAdbDevice.checkNow(adbDevice.serial)
        if (adbDevice.status!= currentStatus) {
            adbDevice.status = currentStatus
            adbDeviceRepository.save(adbDevice)
        }
        return adbDevice
    }
}