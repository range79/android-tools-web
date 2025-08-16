package com.range.firmware.exception

import com.range.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class PathNotFoundException(message: String) : AbstractExceptionHandler(message, HttpStatus.NOT_FOUND) {
}