package com.range.adb.service.flasher

import com.range.adb.dto.AdbDeviceResponseDto
import com.range.adb.enums.AdbDeviceStatus
import com.range.adb.service.helper.AdbCommandHelper
import com.range.common.exception.WrongFirmwareTypeException
import com.range.firmware.enum.FirmwareType
import com.range.firmware.service.FirmwareService
import org.springframework.stereotype.Service

@Service
class AdbFirmwareFlasher (
    private val firmwareService: FirmwareService,
    private val adbCommandHelper: AdbCommandHelper
){
    fun install(firmwareId: Long, deviceId: Long): AdbDeviceResponseDto {
        val firmware =firmwareService.findById(firmwareId)
        if (firmware.firmwareType!= FirmwareType.ADB_ROM|| firmware.firmwareType!= FirmwareType.UPDATE_ZIP){
            throw WrongFirmwareTypeException("FirmwareType ${firmware.firmwareType} not supported on adb installation")
        }
        return adbCommandHelper.executeCommand(deviceId,"Total","sideload ${firmware.filepath}", updateStatus = AdbDeviceStatus.Connected)
    }
}