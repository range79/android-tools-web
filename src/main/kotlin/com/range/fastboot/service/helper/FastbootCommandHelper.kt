package com.range.fastboot.service.helper

import com.range.common.util.WrapperUtil
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import com.range.fastboot.util.CheckDevices
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FastbootCommandHelper (
    private val fastbootRepository: FastbootDevicesInfoRepository,
    private val wrapperUtil: WrapperUtil,
    private val checkDevices: CheckDevices
){
    @Transactional(readOnly = true)
    fun getDeviceOrThrow(id: Long): FastbootDeviceInfo {
        return fastbootRepository.findById(id).orElseThrow {
            FastbootDeviceNotFoundException("Device not found with id $id")
        }
    }

    fun checkDeviceIsAlive(serial: String) {
        if (checkDevices.checkNow(serial) != FastbootDeviceStatus.Connected) {
            throw FastbootDeviceNotFoundException("Device [$serial] is not connected")
        }
    }

    @Transactional
    fun executeCommand(id: Long,
                       expectedOutputContains: String?=null,
                       command: String,
                       updateStatus: FastbootDeviceStatus?=null): FastbootDeviceResponseDto {

        val device = getDeviceOrThrow(id)
        checkDeviceIsAlive(device.serial)

        val output = wrapperUtil.getFastbootOutput(device.serial, command)

        if (expectedOutputContains != null && !output.contains(expectedOutputContains, ignoreCase = true)) {
            throw FastbootDeviceNotFoundException("Fastboot command failed. Output: $output")
        }

        if (updateStatus != null) {
            device.fastbootDeviceStatus = updateStatus
            fastbootRepository.save(device)
        }

        return FastbootDeviceResponseDto(device.serial,
            device.fastbootDeviceStatus)
    }




}
