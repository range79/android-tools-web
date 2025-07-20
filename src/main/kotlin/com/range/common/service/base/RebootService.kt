package com.range.common.service.base


interface RebootService<T>{
     fun reboot(id: Long):T
     fun rebootFastboot(id: Long):T
     fun rebootFastbootD(id: Long):T
     fun rebootRecovery(id: Long):T
}