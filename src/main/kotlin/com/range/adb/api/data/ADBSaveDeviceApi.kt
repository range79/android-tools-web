package com.range.adb.api.data

import com.range.adb.dto.AdbDeviceResponseDto
import com.range.common.api.BaseSaveDevicesApi
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/adb")
interface ADBSaveDeviceApi: BaseSaveDevicesApi<AdbDeviceResponseDto> {
}