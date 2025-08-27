package com.range.firmware.api

import com.range.firmware.domain.model.Firmware
import com.range.firmware.enum.FirmwareType
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
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
    @PostMapping("/rom/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun saveRom(
        @Parameter(
            description = "Rom file",
            required = true,
            content = [Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)]
        )
        @RequestPart("file") multipartFile: MultipartFile,

        @Parameter(description = "Firmware type", required = true)
        @RequestParam("firmwareType") firmwareType: FirmwareType
    )

}