package com.range.fastboot.service.remove

import com.range.fastboot.dto.FastbootDeviceResponseDto

interface FastbootRemoveService {
    fun removePartition(id: Long,partition: String) : FastbootDeviceResponseDto




}