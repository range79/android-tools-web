package com.range.fastboot.enums

import java.util.Locale.getDefault

enum class PartitionOptions {
    Boot,
    System,
    Cust,
    Vendor;
    //l will add more options (later)


    fun toLower(): String {
        return name.lowercase(getDefault())

    }
}