package com.range.fastboot.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

interface FastbootService {
    fun  getDevices(): String
}