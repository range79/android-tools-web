package com.range.backup.controller

import com.range.backup.api.FileSaveApi
import com.range.backup.service.FileSaveService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
@RestController
class FileSaveController(
    private val fileSaveService: FileSaveService
): FileSaveApi {
    override fun saveToLocal(file: MultipartFile) {
        return
    }

    override fun saveToDropBox(file: MultipartFile) {
        TODO("Not yet implemented")
    }
}