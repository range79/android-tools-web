package com.range.fastboot.service.basic

import com.range.common.service.base.BasicCommandsService
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.PartitionOptions

interface BasicFastbootCommandsService: BasicCommandsService<FastbootDeviceResponseDto>{
    fun removePartition(id: Long, partition: PartitionOptions) : FastbootDeviceResponseDto
}