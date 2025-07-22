package com.range.fastboot.service.reboot.impl

import com.range.common.enums.RebootOptions
import com.range.fastboot.dto.FastbootDeviceResponseDto
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.service.helper.FastbootCommandHelper
import com.range.fastboot.service.reboot.FastbootRebootService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FastbootRebootServiceImpl(
    private val helper: FastbootCommandHelper
) : FastbootRebootService {
    @Transactional
    override fun reboot(id: Long,option: RebootOptions): FastbootDeviceResponseDto {

        return helper.executeCommand(
            id=id,
            expectedOutputContains = "Rebooting",
            updateStatus = FastbootDeviceStatus.Disconnected,
            command= "reboot ${option.toLower()}"
        )}

}
