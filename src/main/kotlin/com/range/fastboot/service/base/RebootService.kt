package com.range.fastboot.service.base

import reactor.core.publisher.Flux

interface RebootService {
    fun reboot(): Flux<String>
    fun rebootFastboot(): Flux<String>
    fun rebootFastbootD(): Flux<String>
    fun rebootRecovery(): Flux<String>
}