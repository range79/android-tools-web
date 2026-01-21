package com.range.backup.controller

import com.range.backup.api.FileSaveApi
import com.range.backup.dto.DropBoxFileSaveRequest
import com.range.backup.dto.FileSaveResponse
import com.range.backup.service.FileSaveService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
@RestController
class FileSaveController(
    private val fileSaveService: FileSaveService
): FileSaveApi {
    override fun saveToLocal(file: MultipartFile) : FileSaveResponse{
        return fileSaveService.saveFileToLocal(file)
    }

    override fun saveDropBox(dropBoxSaveRequest: DropBoxFileSaveRequest): FileSaveResponse {
        return fileSaveService.saveToDropBox(dropBoxSaveRequest)
    }
}