package com.range.fastboot.api

import com.range.fastboot.dto.FastbootDeviceResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/{id}/reboot")
interface FastbootRebootDeviceApi {
    @GetMapping()
    fun reboot(@PathVariable id : Long): ResponseEntity<FastbootDeviceResponseDto>
    @GetMapping("/fastboot")
    fun rebootFastboot(@PathVariable id : Long):  ResponseEntity<FastbootDeviceResponseDto>
    @GetMapping("/fastbootd")
    fun rebootFastbootD(@PathVariable id : Long):  ResponseEntity<FastbootDeviceResponseDto>
    @GetMapping("/recovery")
    fun rebootRecovery(@PathVariable id : Long):  ResponseEntity<FastbootDeviceResponseDto>
}