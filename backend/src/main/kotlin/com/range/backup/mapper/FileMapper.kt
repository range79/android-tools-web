package com.range.backup.mapper

import com.range.backup.domain.entity.FileEntity
import com.range.backup.dto.FileSaveResponse
import org.springframework.stereotype.Component

@Component
class FileMapper {
    fun toResponse(fileEntity: FileEntity): FileSaveResponse {
        return FileSaveResponse(
            fileEntity.id,
            fileEntity.name,
            fileEntity.path,
            fileEntity.fileSaveType
        )
    }
}