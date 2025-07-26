package com.range.error.service.impl

import com.range.error.domain.entity.ErrorEntity
import com.range.error.domain.repository.ErrorRepository
import com.range.error.service.ErrorService
import org.springframework.stereotype.Service

@Service
class ErrorServiceImpl(
    private val errorRepository: ErrorRepository
): ErrorService {
    override fun getErrors(): List<ErrorEntity> {
        return errorRepository.findAll()
    }

    override fun deleteErrors() {
        return errorRepository.deleteAll()
    }

    override fun saveError(error: ErrorEntity): ErrorEntity {
        return errorRepository.save(error)
    }
}