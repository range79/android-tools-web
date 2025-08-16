package com.range.adb.api.data

import com.range.adb.domain.entity.AdbDevice
import com.range.common.api.CommonDevicesFetcherApi
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/adb/devices")
interface AdbDevicesFetcherApi: CommonDevicesFetcherApi<AdbDevice>{

}