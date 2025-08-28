package com.range.fastboot.service.flasher

import com.range.fastboot.enums.PartitionOptions
import java.util.UUID

interface FastbootFirmwareFlasher {
    fun flash(deviceId: Long, firmwareId: UUID,partitionOptions: PartitionOptions)
}