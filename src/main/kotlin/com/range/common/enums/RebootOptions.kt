package com.range.common.enums

import java.util.Locale.getDefault

enum class RebootOptions {
    Recovery,
    Bootloader,
    Fastboot,
    System;
    fun toLower(): String {
        return name.lowercase(getDefault())

    }
}