package com.range.fastboot.service.remove

import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.PartitionOptions

interface FastbootRemoveService {
    fun removePartition(id: Long, partition: PartitionOptions) : FastbootDeviceResponseDto




}