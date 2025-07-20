package com.range.fastboot.api

import com.range.fastboot.dto.FastbootDeviceResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/{id}/reboot")
interface FastbootRebootApi {
    @GetMapping()
    fun reboot(@PathVariable id : Long): FastbootDeviceResponseDto
    @GetMapping("/fastboot")
    fun rebootFastboot(@PathVariable id : Long): FastbootDeviceResponseDto
    @GetMapping("/fastbootd")
    fun rebootFastbootD(@PathVariable id : Long): FastbootDeviceResponseDto
    @GetMapping("/recovery")
    fun rebootRecovery(@PathVariable id : Long): FastbootDeviceResponseDto
}