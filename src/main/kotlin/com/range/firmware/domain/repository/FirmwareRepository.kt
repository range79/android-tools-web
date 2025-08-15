package com.range.firmware.domain.repository

import com.range.firmware.domain.model.Firmware
import org.springframework.data.jpa.repository.JpaRepository

interface FirmwareRepository: JpaRepository<Firmware, Long> {
}