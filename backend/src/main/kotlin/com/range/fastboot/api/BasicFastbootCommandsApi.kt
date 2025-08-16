package com.range.fastboot.api

import com.range.common.enums.RebootOptions
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.PartitionOptions
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/{id}")
interface BasicFastbootCommandsApi {
    @DeleteMapping("/remoove/partition/{partition}")
    fun removePartition(@PathVariable id: Long,
                        @PathVariable  partition: PartitionOptions) : ResponseEntity<FastbootDeviceResponseDto>


    @GetMapping("/reboot/{option}")
    fun reboot(@PathVariable id : Long,@PathVariable option: RebootOptions): ResponseEntity<FastbootDeviceResponseDto>

}
