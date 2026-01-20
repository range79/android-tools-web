package com.range.backup.exception

import com.range.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class SavedFilePathNotFoundException(msg: String?): AbstractExceptionHandler(
    msg?:"A error occurred when saving the backup file",
    HttpStatus.INTERNAL_SERVER_ERROR
)
