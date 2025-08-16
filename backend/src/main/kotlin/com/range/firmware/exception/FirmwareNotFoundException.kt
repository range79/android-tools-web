package com.range.firmware.exception

import com.range.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class FirmwareNotFoundException(msg:String): AbstractExceptionHandler(msg, HttpStatus.NOT_FOUND) {
}