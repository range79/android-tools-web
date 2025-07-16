package com.range.fastboot.service.reboot.base

import com.range.fastboot.util.WrapperUtil
import reactor.core.publisher.Flux
interface RebootService{
     fun reboot(deviceId: String): Flux<String>
     fun rebootFastboot(deviceId: String): Flux<String>
     fun rebootFastbootD(deviceId: String): Flux<String>
     fun rebootRecovery(deviceId: String): Flux<String>
}