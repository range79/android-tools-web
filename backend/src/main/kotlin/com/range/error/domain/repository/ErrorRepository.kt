package com.range.error.domain.repository

import com.range.error.domain.entity.ErrorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ErrorRepository: JpaRepository<ErrorEntity, Long> {
}