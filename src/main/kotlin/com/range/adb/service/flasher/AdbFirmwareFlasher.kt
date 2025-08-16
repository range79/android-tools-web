package com.range.adb.service.flasher

import com.range.firmware.domain.repository.FirmwareRepository
import com.range.firmware.service.FirmwareService
import org.springframework.stereotype.Service

@Service
class AdbFirmwareFlasher (
    private val firmwareService: FirmwareService,
    private val adbDeviceRepository: FirmwareRepository
){
    fun install(firmwareId: Long,deviceId: Long){

    }
}