package com.range.fastboot.service.basic.impl

import com.range.common.enums.RebootOptions
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.enums.PartitionOptions
import com.range.fastboot.service.basic.BasicFastbootCommandsService
import com.range.fastboot.service.helper.FastbootCommandHelper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BasicFastbootCommandsServiceImpl(
    private val helper: FastbootCommandHelper
) : BasicFastbootCommandsService {

    override fun removePartition(id: Long, partition: PartitionOptions): FastbootDeviceResponseDto {
        return helper.executeCommand(
            id=id,
            expectedOutputContains = "erasing",
            command= "erase ${partition.toLower()}",
            updateStatus = FastbootDeviceStatus.Connected
        )
    }
    @Transactional
    override fun reboot(id: Long,option: RebootOptions): FastbootDeviceResponseDto {

        return helper.executeCommand(
            id=id,
            expectedOutputContains = "Rebooting",
            updateStatus = FastbootDeviceStatus.Disconnected,
            command= "reboot ${option.toLower()}"
        )}

}
