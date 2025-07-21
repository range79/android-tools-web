package com.range.fastboot.api

import com.range.fastboot.dto.FastbootDeviceResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/{id}/remove")
interface FastbootRemoveApi {
    @DeleteMapping("/partition/{partition}")
    fun removePartition(@PathVariable id: Long,
                        @PathVariable partition: String) : ResponseEntity<FastbootDeviceResponseDto>
}