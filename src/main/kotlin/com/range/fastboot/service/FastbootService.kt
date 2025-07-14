package com.range.fastboot.service

import org.springframework.stereotype.Service

interface FastbootService {
    fun writeBoot();
    fun writeRecovery();
    fun writeRom();
    fun writePreloader();
    fun writeSuper();
}