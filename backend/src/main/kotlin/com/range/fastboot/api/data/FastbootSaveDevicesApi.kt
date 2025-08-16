package com.range.fastboot.api.data

import com.range.common.api.BaseSaveDevicesApi
import com.range.fastboot.dto.FastbootDeviceResponseDto
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/")
interface FastbootSaveDevicesApi : BaseSaveDevicesApi<FastbootDeviceResponseDto>{

}