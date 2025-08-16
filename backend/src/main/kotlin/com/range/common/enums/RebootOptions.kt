package com.range.common.enums

enum class RebootOptions {
    Recovery,
    Bootloader,
    Fastboot,
    System;
    fun toLower(): String {
        return name.lowercase()

    }
}