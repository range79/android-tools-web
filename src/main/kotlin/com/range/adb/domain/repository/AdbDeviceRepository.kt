package com.range.adb.domain.repository

import com.range.adb.domain.entity.AdbDevice
import org.springframework.data.jpa.repository.JpaRepository

interface AdbDeviceRepository: JpaRepository<AdbDevice, Long> {
}