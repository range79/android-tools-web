package com.range.firmware.api

import com.range.firmware.domain.model.Firmware
import com.range.firmware.enum.FirmwareType
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/firmware")
interface FirmwareApi {
    @GetMapping("/all")
    fun findAll(
        @PageableDefault(size = 20, sort = ["id"]) pageable: Pageable): Page<Firmware>
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long):Firmware
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long)
    @PostMapping(consumes = arrayOf("multipart/form-data"))
    fun saveRom( @Parameter(description = "ROM dosyası", required = true, content = [Content(mediaType = "multipart/form-data")])

                 @RequestParam("file") multipartFile: MultipartFile,
                 @RequestParam("firmwareType") firmwareType: FirmwareType)


}