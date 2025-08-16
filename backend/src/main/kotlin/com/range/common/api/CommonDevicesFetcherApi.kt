package com.range.common.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface CommonDevicesFetcherApi <T>{
    @GetMapping("/all")
    fun getAllSavedDevices(  @PageableDefault(size = 20, sort = ["id"]) pageable: Pageable): Page<T>
    @GetMapping("/{id}")
    fun getDevice(@PathVariable id: Long):T
}