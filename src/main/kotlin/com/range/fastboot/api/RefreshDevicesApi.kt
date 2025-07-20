package com.range.fastboot.api

import com.range.fastboot.domain.entity.FastbootDeviceInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/device")
interface RefreshDevicesApi{
    @GetMapping("/all")
    fun getAllSavedDevices(): ResponseEntity<List<FastbootDeviceInfo>>
    @GetMapping("/{id}")
    fun getDevice(@PathVariable id: Long): ResponseEntity<FastbootDeviceInfo>}