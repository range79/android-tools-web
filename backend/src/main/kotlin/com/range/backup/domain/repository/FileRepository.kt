package com.range.backup.domain.repository

import com.range.backup.domain.entity.FileEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FileRepository : JpaRepository<FileEntity, UUID>