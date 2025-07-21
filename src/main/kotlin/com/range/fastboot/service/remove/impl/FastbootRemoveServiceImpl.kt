package com.range.fastboot.service.remove.impl

import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.service.helper.FastbootCommandHelper
import com.range.fastboot.service.remove.FastbootRemoveService
import org.springframework.stereotype.Service

@Service
class FastbootRemoveServiceImpl (
private val helper: FastbootCommandHelper
): FastbootRemoveService {
    override fun removePartition(id: Long,partition: String): FastbootDeviceResponseDto {
        return helper.executeCommand(
            id=id,
            expectedOutputContains = "erasing",
            command= "erase $partition",
            updateStatus = FastbootDeviceStatus.Connected
        )
    }




}
