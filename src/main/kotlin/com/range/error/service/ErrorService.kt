package com.range.error.service

import com.range.error.domain.entity.ErrorEntity

interface ErrorService {
    fun getErrors(): List<ErrorEntity>
    fun deleteErrors()
    fun saveError(error: ErrorEntity): ErrorEntity
}