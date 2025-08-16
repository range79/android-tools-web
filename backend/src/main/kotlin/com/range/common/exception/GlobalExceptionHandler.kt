package com.range.common.exception

import com.range.error.domain.entity.ErrorEntity
import com.range.error.service.ErrorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler (
    private val errorService: ErrorService

) {

    @ExceptionHandler(AbstractExceptionHandler::class)
    fun handleAbstractException(exception: AbstractExceptionHandler): ResponseEntity<ErrorEntity>  {

        val error = errorService.saveError(ErrorEntity(
            id = null,
            errorCode = exception.status.value(),
            errorMessage = exception.message?:"Unknown error",
            timeStamp = LocalDateTime.now()
        ))

        return ResponseEntity.status(exception.status).body(error)
    }
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorEntity> {
        val error  =  errorService.saveError(ErrorEntity(
            id = null,
            errorCode = 500,
            errorMessage = exception.message?:"Unknown error",
            timeStamp = LocalDateTime.now()
        ))

        return ResponseEntity.internalServerError().body(error);
    }



}