package com.range.adb.service.helper

import com.range.adb.domain.entity.AdbDevice
import com.range.adb.domain.repository.AdbDeviceRepository
import com.range.adb.dto.AdbDeviceResponseDto
import com.range.adb.enums.AdbDeviceStatus
import com.range.adb.exception.AdbDeviceNotFoundException
import com.range.adb.util.CheckAdbDevice
import com.range.common.service.helper.base.CommandHelper
import com.range.common.util.WrapperUtil
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AdbCommandHelper(
    private val repo: AdbDeviceRepository,
    private val checker: CheckAdbDevice,
    private val wrapperUtil: WrapperUtil
): CommandHelper<AdbDevice, AdbDeviceResponseDto, AdbDeviceStatus > {
        @Transactional(readOnly = true)
        override fun getDeviceOrThrow(id: Long): AdbDevice  {
            return repo.findById(id).orElseThrow {
                AdbDeviceNotFoundException("Device not found with id $id")
            }
        }

        override fun checkDeviceIsAlive(serial: String) {
            if (checker.checkNow(serial) != AdbDeviceStatus.Connected) {
                throw FastbootDeviceNotFoundException("Device [$serial] is not connected")
            }
        }

        @Transactional
        override fun executeCommand(
            id: Long,
            expectedOutputContains: String?,
            command: String,
            updateStatus: AdbDeviceStatus?
        ): AdbDeviceResponseDto {

            val device = getDeviceOrThrow(id)
            checkDeviceIsAlive(device.serial)

            val output = wrapperUtil.getAdbOutput(device.serial, command)

            if (expectedOutputContains != null && !output.contains(expectedOutputContains, ignoreCase = true)) {
                throw AdbDeviceNotFoundException("Adb command failed. Output: $output")
            }

            if (updateStatus != null) {
                device.status = updateStatus
                repo.save(device)
            }

            return AdbDeviceResponseDto(device.serial,
                device.status)
        }

    }