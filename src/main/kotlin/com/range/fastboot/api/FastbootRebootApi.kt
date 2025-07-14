package com.range.fastboot.api

import reactor.core.publisher.Flux

interface FastbootRebootApi {

    fun reboot(): Flux<String>
    fun rebootFastboot();
    fun rebootFastbootD();
    fun rebootRecovery();
}