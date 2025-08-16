package com.range.common.exception

import org.springframework.http.HttpStatus

class WrongFirmwareTypeException(msg: String): AbstractExceptionHandler(msg, HttpStatus.BAD_REQUEST) {
}