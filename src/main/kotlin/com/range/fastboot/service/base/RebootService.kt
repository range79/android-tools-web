package com.range.fastboot.service.base

interface RebootService {
    fun reboot();
    fun rebootFastboot();
    fun rebootFastbootD();
    fun rebootRecovery();
}