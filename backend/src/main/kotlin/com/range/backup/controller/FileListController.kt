package com.range.backup.controller

import com.range.backup.api.FileListApi
import com.range.backup.domain.entity.FileSaveType
import com.range.backup.dto.FileSaveResponse
import com.range.backup.service.FileListService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class FileListController(private val fileListService: FileListService) : FileListApi {
    override fun listWithFileType(
        fileType: FileSaveType,
        pageable: Pageable
    ): Page<FileSaveResponse> {
        return fileListService.listFilesBySaveType(fileType, pageable)
    }

    override fun listAllFile(pageable: Pageable): Page<FileSaveResponse> {
      return fileListService.listAllFiles(pageable)
    }
}