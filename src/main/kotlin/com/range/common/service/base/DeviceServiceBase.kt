package com.range.common.service.base

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DeviceServiceBase <T>{
    fun getAllDevices(pageable: Pageable): Page<T>
    fun getOneDevice(id: Long): T
}
