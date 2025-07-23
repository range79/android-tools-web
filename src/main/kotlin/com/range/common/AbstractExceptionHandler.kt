package com.range.common

import org.springframework.http.HttpStatus

open class AbstractExceptionHandler(msg:String, val status: HttpStatus): Exception(msg) {

}