package com.range.fastboot.api.data

import com.range.fastboot.dto.FastbootDeviceResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/")
interface SaveDevicesApi {
    @GetMapping("/scanDevices")
    fun scanDevices() : ResponseEntity<List<String>>
    @PostMapping("/saveDevice/{id}")
    fun saveDevice(@PathVariable id:String): ResponseEntity<FastbootDeviceResponseDto>
}