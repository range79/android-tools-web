package com.range.adb.reboot.base


import reactor.core.publisher.Flux
interface RebootService{
     fun reboot(deviceId: String): Flux<String>
     fun rebootFastboot(deviceId: String): Flux<String>
     fun rebootFastbootD(deviceId: String): Flux<String>
     fun rebootRecovery(deviceId: String): Flux<String>
}