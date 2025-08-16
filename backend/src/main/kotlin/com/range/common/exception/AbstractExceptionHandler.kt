package com.range.common.exception

import org.springframework.http.HttpStatus

open class AbstractExceptionHandler(msg:String, val status: HttpStatus): RuntimeException(msg) {

}