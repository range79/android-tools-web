package com.range.common.service.base

interface DeviceServiceBase <T>{
    fun getAllDevices(): List<T>
    fun getOneDevice(id: Long): T
}
