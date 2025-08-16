package com.range.fastboot.api.data

import com.range.common.api.CommonDevicesFetcherApi
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/fastboot/device")
interface FastbootDevicesFetcherApi: CommonDevicesFetcherApi<FastbootDeviceInfo> {

}