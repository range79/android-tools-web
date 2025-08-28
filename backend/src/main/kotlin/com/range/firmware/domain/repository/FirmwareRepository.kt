package com.range.firmware.domain.repository

import com.range.firmware.domain.model.Firmware
import com.range.firmware.enum.FirmwareType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FirmwareRepository: JpaRepository<Firmware, UUID> {
   fun  findAllByFirmwareType(pageable: Pageable,firmwareType: FirmwareType): Page<Firmware>
}