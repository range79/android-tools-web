package com.range.firmware.service

import com.range.firmware.domain.model.Firmware
import com.range.firmware.enum.FirmwareType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

interface FirmwareService {
    fun findAll(pageable: Pageable): Page<Firmware>
    fun findById(id: Long):Firmware
    fun deleteById(id: Long)
    fun saveRom(multipartFile: MultipartFile,firmwareType: FirmwareType)
    fun findByType(firmwareType: FirmwareType,pageable: Pageable): Page<Firmware>
}