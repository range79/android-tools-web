package com.range.firmware.controller

import com.range.firmware.api.FirmwareApi
import com.range.firmware.domain.model.Firmware
import com.range.firmware.enum.FirmwareType
import com.range.firmware.service.FirmwareService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FirmwareController(
    private val firmwareService: FirmwareService
): FirmwareApi {

    override fun findAll(pageable: Pageable): Page<Firmware> {
        return firmwareService.findAll(pageable)
    }

    override fun findById(id: Long): Firmware {
        return firmwareService.findById(id)
    }

    override fun deleteById(id: Long) {
        return firmwareService.deleteById(id)
    }

    override fun saveRom(
        multipartFile: MultipartFile,
        firmwareType: FirmwareType,
    ) {
        return firmwareService.saveRom(multipartFile, firmwareType)
    }
}