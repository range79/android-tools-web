package com.range.backup.dto

import com.range.backup.domain.entity.FileSaveType
import java.util.UUID

data class FileSaveResponse (
    var id: UUID? = null,
    var name: String,
    var path: String,
    var fileSaveType: FileSaveType
)