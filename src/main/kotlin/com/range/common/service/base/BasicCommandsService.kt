package com.range.common.service.base

import com.range.common.enums.RebootOptions


interface BasicCommandsService<T>{
     fun reboot(id: Long,option: RebootOptions):T

}