package com.range.error.api

import com.range.error.domain.entity.ErrorEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/error")
interface ErrorApi {
    @GetMapping("/all")
    fun getErrors(): ResponseEntity<List<ErrorEntity>>
    @DeleteMapping("/all")
    fun deleteErrors(): ResponseEntity<Unit?>
}