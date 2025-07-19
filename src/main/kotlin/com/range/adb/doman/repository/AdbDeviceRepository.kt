package com.range.adb.doman.repository

import com.range.adb.doman.entity.AdbDevice
import org.springframework.data.jpa.repository.JpaRepository

interface AdbDeviceRepository: JpaRepository<AdbDevice, Long> {
}