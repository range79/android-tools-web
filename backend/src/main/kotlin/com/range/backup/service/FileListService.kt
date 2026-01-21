package com.range.backup.service

import com.range.backup.domain.entity.FileSaveType
import com.range.backup.dto.FileSaveResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FileListService {
    fun listFiles(fileSaveType: FileSaveType,pageable: Pageable): Page<FileSaveResponse>
    fun listAllFiles(pageable: Pageable): Page<FileSaveResponse>
}