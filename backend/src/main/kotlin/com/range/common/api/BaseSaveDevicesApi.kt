package com.range.common.api

import com.range.adb.dto.AdbDeviceResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

interface BaseSaveDevicesApi <T>{
    @GetMapping("/scanDevices")
    fun scanDevices() : ResponseEntity<List<String>>
    @PostMapping("/saveDevice/{id}")
    fun saveDevice(@PathVariable id:String): ResponseEntity<AdbDeviceResponseDto>
}