package com.range.fastboot.controller

import com.range.common.enums.RebootOptions
import com.range.fastboot.api.BasicFastbootCommandsApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.PartitionOptions
import com.range.fastboot.service.basic.BasicFastbootCommandsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicFastbootCommandsController(
    private val service: BasicFastbootCommandsService
): BasicFastbootCommandsApi {
    override fun removePartition(id: Long,
                                 partition: PartitionOptions
    ): ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(service.removePartition(id,partition))
    }
    override fun reboot(id: Long,option: RebootOptions):ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(service.reboot(id,option))
    }
}