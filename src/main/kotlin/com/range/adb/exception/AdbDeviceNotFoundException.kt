package com.range.adb.exception

import com.range.common.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class AdbDeviceNotFoundException(msg:String): AbstractExceptionHandler(msg, HttpStatus.NOT_FOUND) {
}