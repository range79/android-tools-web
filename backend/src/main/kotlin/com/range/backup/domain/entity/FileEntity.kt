package com.range.backup.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
@Entity
@Table(name = "backup_file")
data class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    val name: String,
    val size: Long,
    var path: String?,
    val fileSaveType: FileSaveType,
)