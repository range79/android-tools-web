package com.range.common.dto

import java.time.LocalDateTime

data class ErrorResponse (
    val errorCode: String,
    val errorMessage: String?,
    val timeStamp: LocalDateTime
)