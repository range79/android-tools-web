package com.range.adb.service.reboot.impl

import com.range.adb.dto.AdbDeviceDto
import com.range.adb.enums.AdbDeviceStatus
import com.range.adb.service.helper.AdbCommandHelper
import com.range.adb.service.reboot.BasicAdbCommandsService
import com.range.common.enums.RebootOptions
import org.springframework.stereotype.Service

@Service
class BasicAdbCommandsServiceImpl(
    private val helper: AdbCommandHelper
): BasicAdbCommandsService {
    override fun reboot(id: Long, option: RebootOptions): AdbDeviceDto {
        return helper.executeCommand(
            id=id,
            expectedOutputContains = "Rebooting",
            updateStatus = AdbDeviceStatus.Disconnected,
            command= "reboot ${option.toLower()}"
        )}

    }