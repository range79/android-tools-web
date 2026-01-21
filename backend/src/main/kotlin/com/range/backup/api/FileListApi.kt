package com.range.backup.api

import com.range.backup.domain.entity.FileSaveType
import com.range.backup.dto.FileSaveResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/fileList"], produces = ["application/json"])
interface FileListApi {
    @GetMapping("/list/{fileType}")
    fun listWithFileType(
        @PathVariable fileType: FileSaveType,
        pageable: Pageable
    ): Page<FileSaveResponse>

    @GetMapping("/list")
    fun listAllFile(pageable: Pageable): Page<FileSaveResponse>
}