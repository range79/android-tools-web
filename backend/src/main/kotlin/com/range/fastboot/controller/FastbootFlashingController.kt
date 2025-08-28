package com.range.fastboot.controller

import com.range.fastboot.api.FastbootFlashingApi
import com.range.fastboot.enums.PartitionOptions
import com.range.fastboot.service.flasher.FastbootFirmwareFlasher
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
@RestController
class FastbootFlashingController (
    private val service: FastbootFirmwareFlasher
): FastbootFlashingApi {
    override fun flash(
        deviceId: Long,
        firmwareId: UUID,
        partitionOptions: PartitionOptions
    ) {
        return service.flash(deviceId, firmwareId, partitionOptions)
    }
}
