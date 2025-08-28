package com.range.firmware.service

import com.range.firmware.domain.model.Firmware
import com.range.firmware.enum.FirmwareType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

interface FirmwareService {
    fun findAll(pageable: Pageable): Page<Firmware>
    fun findById(id: UUID):Firmware
    fun deleteById(id: UUID)
    fun saveRom(multipartFile: MultipartFile,firmwareType: FirmwareType)
    fun findByType(firmwareType: FirmwareType,pageable: Pageable): Page<Firmware>
}