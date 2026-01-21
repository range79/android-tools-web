package com.range.backup.domain.repository

import com.range.backup.domain.entity.FileEntity
import com.range.backup.domain.entity.FileSaveType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FileRepository : JpaRepository<FileEntity, UUID> {
    fun findAllByFileSaveType(fileSaveType: FileSaveType, pageable: Pageable): Page<FileEntity>
}