package com.range.fastboot.service.reboot.impl

import com.range.common.util.WrapperUtil
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import com.range.fastboot.service.reboot.FastbootRebootService
import com.range.fastboot.util.CheckDevices
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FastbootRebootServiceImpl(
    private val fastbootRepository: FastbootDevicesInfoRepository,
    private val wrapperUtil: WrapperUtil,
    private val checkDevices: CheckDevices
) : FastbootRebootService {
    @Transactional
    override fun reboot(id: Long): FastbootDeviceResponseDto {
        return  rebootDevice(id,"reboot", FastbootDeviceStatus.Disconnected)
    }
    @Transactional
    override fun rebootFastboot(id: Long): FastbootDeviceResponseDto {
        return rebootDevice(id,"reboot bootloader", FastbootDeviceStatus.Disconnected)
    }
    @Transactional
    override fun rebootFastbootD(id: Long): FastbootDeviceResponseDto {
        return rebootDevice(id,"reboot fastboot", FastbootDeviceStatus.Disconnected)
    }
    @Transactional
    override fun rebootRecovery(id: Long): FastbootDeviceResponseDto {
        return rebootDevice(id,"reboot recovery", FastbootDeviceStatus.Recovery_mode)
    }


    private fun checkDeviceIsAlive(serial: String) {
        if (checkDevices.checkNow(serial)!= FastbootDeviceStatus.Connected){
            throw FastbootDeviceNotFoundException("device not connected")
        }
    }

    private fun  checkDeviceReboot(output: String, id: Long) {
        if (!output.contains("Rebooting")){
            throw FastbootDeviceNotFoundException("An error occurred when reboot phone with id $id")
        }
    }


    private fun rebootDevice(id: Long,command: String,status: FastbootDeviceStatus): FastbootDeviceResponseDto{
        val device = fastbootRepository.findById(id).orElseThrow{
            FastbootDeviceNotFoundException("device not found with id $id")
        }
        checkDeviceIsAlive(device.serial)
        val deviceOutput =wrapperUtil.getFastbootOutput(device.serial,command)
        checkDeviceReboot(deviceOutput,device.id!!)
        device.fastbootDeviceStatus = status
        return FastbootDeviceResponseDto(device.serial,device.fastbootDeviceStatus)
    }
}
