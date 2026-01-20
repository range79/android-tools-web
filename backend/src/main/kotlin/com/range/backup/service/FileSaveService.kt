package com.range.backup.service

import com.range.backup.dto.DropBoxFileSaveRequest
import com.range.backup.dto.FileSaveResponse
import org.springframework.web.multipart.MultipartFile

interface FileSaveService {
    fun saveFileToLocal(multipartFile: MultipartFile): FileSaveResponse
    fun saveToDropBox(dropBoxFileSaveRequest: DropBoxFileSaveRequest):FileSaveResponse
}