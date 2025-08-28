package com.range.fastboot.service.flasher.impl

import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.enums.PartitionOptions
import com.range.fastboot.service.devices.FastbootDeviceService
import com.range.fastboot.service.flasher.FastbootFirmwareFlasher
import com.range.fastboot.service.helper.FastbootCommandHelper
import com.range.firmware.service.FirmwareService
import org.springframework.stereotype.Service
import java.util.UUID
@Service
class FastbootFirmwareFlasherImpl(
    private val firmwareService: FirmwareService,
    private val fastbootDeviceService: FastbootDeviceService,
    private val fastbootCommandHelper: FastbootCommandHelper
): FastbootFirmwareFlasher {
    override fun flash(
        deviceId: Long,
        firmwareId: UUID,
        partitionOptions: PartitionOptions
    ) {
        val device = fastbootDeviceService.getOneDevice(deviceId)
        val firmware = firmwareService.findById(firmwareId)
        fastbootCommandHelper.executeCommand(device.id!!,"OKAY","fastboot flash ${partitionOptions} ${firmware.filepath}", FastbootDeviceStatus.Connected)

    }
}