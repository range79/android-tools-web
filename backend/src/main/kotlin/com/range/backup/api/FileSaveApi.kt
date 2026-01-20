package com.range.backup.api

import com.range.backup.dto.FileSaveResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
@RequestMapping("/backup")
interface FileSaveApi {
    @PostMapping("/local",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE] )
    fun saveToLocal(@RequestPart file: MultipartFile): FileSaveResponse
}