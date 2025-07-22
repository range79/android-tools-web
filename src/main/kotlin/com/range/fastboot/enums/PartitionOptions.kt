package com.range.fastboot.enums

import java.util.Locale.getDefault

enum class PartitionOptions {
    Boot,
    System;

    fun toLower(): String {
        return name.lowercase(getDefault())

    }
}