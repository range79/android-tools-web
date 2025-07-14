package com.range.fastboot.util

import org.springframework.stereotype.Component
import java.util.Locale.getDefault

@Component
class WrapperUtil {

    fun getFastboot(): String{
        val os = System.getProperty("os.name").lowercase(getDefault())

        return if (os.contains("win")) {
            "fastboot.exe"
        } else {
            "fastboot"
        }
    }


fun getAdb(): String{
    val os = System.getProperty("os.name").lowercase(getDefault())
    return if (os.contains("win")) {
        "adb.exe"
    }
    else {
        "adb"
    }
}




}

