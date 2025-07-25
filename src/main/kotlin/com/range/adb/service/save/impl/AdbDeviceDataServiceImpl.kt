package com.range.adb.service.save.impl

import com.range.adb.domain.entity.AdbDevice
import com.range.adb.domain.repository.AdbDeviceRepository
import com.range.adb.dto.AdbDeviceResponseDto
import com.range.adb.enums.AdbDeviceStatus
import com.range.adb.service.save.AdbDeviceDataService
import com.range.common.util.WrapperUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdbDeviceDataServiceImpl(
    private val adbDeviceRepository: AdbDeviceRepository,
    private val wrapperUtil: WrapperUtil
): AdbDeviceDataService {
    override fun scanDevices() : List<String> {
        val output = wrapperUtil.getAdbOutput(null, "devices")
        return output.lines()
            .filter { it.contains("adb") }
            .map { it.substringBefore("\t").trim() }
    }


    override fun getCodename(serial: String): String? {
        return wrapperUtil.getAdbOutput(serial, "shell getprop ro.product.device")
    }

    override fun getAndroidVersion(serial: String): String {
        return wrapperUtil.getAdbOutput(serial, "shell getprop ro.build.version.release")
    }
    @Transactional
    override fun saveDevice(serial: String): AdbDeviceResponseDto {
        val adbDevice= AdbDevice(
            serial = serial,
            codename = getCodename(serial),
            androidVersion = getAndroidVersion(serial),
            status = AdbDeviceStatus.Connected
        )
        adbDeviceRepository.save(adbDevice)
        return AdbDeviceResponseDto(
            serial = serial,
            adbDeviceStatus = adbDevice.status
        )
    }
}