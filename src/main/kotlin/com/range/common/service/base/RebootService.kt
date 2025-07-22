package com.range.common.service.base

import com.range.common.enums.RebootOptions


interface RebootService<T>{
     fun reboot(id: Long,option: RebootOptions):T
}