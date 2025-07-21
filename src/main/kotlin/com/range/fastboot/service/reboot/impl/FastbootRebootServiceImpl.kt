package com.range.fastboot.service.reboot.impl

import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.service.helper.FastbootCommandHelper
import com.range.fastboot.service.reboot.FastbootRebootService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FastbootRebootServiceImpl(
    private val helper: FastbootCommandHelper
) : FastbootRebootService {
    @Transactional
    override fun reboot(id: Long): FastbootDeviceResponseDto {
        return helper.executeCommand(
            id=id,
            expectedOutputContains = "Rebooting",
            command= "reboot",
            updateStatus = FastbootDeviceStatus.Disconnected
        )
    }
    @Transactional
    override fun rebootFastboot(id: Long): FastbootDeviceResponseDto {
        return helper.executeCommand(
            id=id,
            expectedOutputContains = "Rebooting",
            command= "reboot bootloader",
            updateStatus = FastbootDeviceStatus.Disconnected
        )
    }
    @Transactional
    override fun rebootFastbootD(id: Long): FastbootDeviceResponseDto {
        return helper.executeCommand(
            id=id,
            expectedOutputContains = "Rebooting",
            command= "reboot fastboot",
            updateStatus = FastbootDeviceStatus.Disconnected
        )
    }
    @Transactional
    override fun rebootRecovery(id: Long): FastbootDeviceResponseDto {
        return helper.executeCommand(
            id=id,
            expectedOutputContains = "Rebooting",
            command= "reboot fastboot",
            updateStatus = FastbootDeviceStatus.Disconnected
        )
    }



}
