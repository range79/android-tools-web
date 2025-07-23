package com.range.adb.api

import com.range.adb.dto.AdbDeviceDto
import com.range.common.enums.RebootOptions
import com.range.fastboot.dto.FastbootDeviceResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping()
interface  BasicAdbCommandApi {
    @GetMapping("/reboot/{option}")
    fun reboot(@PathVariable id : Long,@PathVariable option: RebootOptions): ResponseEntity<AdbDeviceDto>

}