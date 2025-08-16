package com.range.error.controller

import com.range.error.api.ErrorApi
import com.range.error.domain.entity.ErrorEntity
import com.range.error.service.ErrorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ErrorController(
    private val service: ErrorService
): ErrorApi {
    override fun getErrors(): ResponseEntity<List<ErrorEntity>> {
     return   ResponseEntity.ok().body(service.getErrors());
    }

    override fun deleteErrors(): ResponseEntity<Unit?> {
      return   ResponseEntity.ok().body(service.deleteErrors())
    }


}