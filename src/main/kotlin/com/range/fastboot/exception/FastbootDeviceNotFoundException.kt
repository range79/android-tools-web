package com.range.fastboot.exception

import com.range.common.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class FastbootDeviceNotFoundException(message: String): AbstractExceptionHandler(message, HttpStatus.NOT_FOUND)