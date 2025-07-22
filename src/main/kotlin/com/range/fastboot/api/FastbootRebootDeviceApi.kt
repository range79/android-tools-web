package com.range.fastboot.api

import com.range.common.enums.RebootOptions
import com.range.fastboot.dto.FastbootDeviceResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping()
interface FastbootRebootDeviceApi {
    @GetMapping("/fastboot/{id}/reboot/{option}")
    fun reboot(@PathVariable id : Long,@PathVariable option: RebootOptions): ResponseEntity<FastbootDeviceResponseDto>}