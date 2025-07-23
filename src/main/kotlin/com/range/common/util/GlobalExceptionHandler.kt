package com.range.common.util

import com.range.common.AbstractExceptionHandler
import com.range.common.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {
@ExceptionHandler(AbstractExceptionHandler::class)
    fun handle(exception: AbstractExceptionHandler): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(exception.status.value()).body(
            ErrorResponse(
                errorCode = exception.status.name,
                errorMessage = exception.message,
                timeStamp = LocalDateTime.now()
            )
        )

    }


}