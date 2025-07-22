package com.range.fastboot.controller

import com.range.fastboot.api.FastbootRemoveApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.PartitionOptions
import com.range.fastboot.service.remove.FastbootRemoveService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class FastbootRemoveController(
    private val service: FastbootRemoveService
): FastbootRemoveApi {
    override fun removePartition(id: Long,
                                 partition: PartitionOptions
    ): ResponseEntity<FastbootDeviceResponseDto> {
        return ResponseEntity.ok(service.removePartition(id,partition))
    }
}