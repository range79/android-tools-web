package com.range.common.api

import com.range.adb.domain.entity.AdbDevice
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface CommonDevicesFetcherApi <T: Any>{
    @GetMapping("/all")
    fun getAllSavedDevices(  @PageableDefault(size = 20, sort = ["id"]) pageable: Pageable): Page<T>
    @GetMapping("/{id}")
    fun getDevice(@PathVariable id: Long):T
    @GetMapping("/list")
    fun getAllSavedDevices():List<T>
}