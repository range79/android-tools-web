package com.range.fastboot.enums

import java.util.Locale.getDefault

enum class PartitionOptions {
    // Boot & Recovery
    Boot,
    Recovery,
    InitBoot,
    VendorBoot,

    // System & Vendor
    System,
    SystemExt,
    Vendor,
    Product,
    Cust,
    ODM,

    // User data
    Userdata,
    Cache,
    Metadata,
    Misc,
    Persist,

    // Verified boot
    Vbmeta,
    VbmetaSystem,
    VbmetaVendor,

    // Kernel / Device Tree
    Dtbo,
    Logo,
    Splash,

    // Dynamic partitions
    Super,

    // Radio / Modem
    Modem,
    Modemst1,
    Modemst2,
    Fsg,
    Fsc,
    DDR,

    // EFS & Calibration
    Efs,
    Bluetooth,
    Wifi,
    Nvdata,
    Nvcfg,
    Nvram,

    // Other
    Keymaster,
    Gatekeeper,
    Oem,
    Frp,
    Protect1,
    Protect2,
    Seccfg;

    fun toLower(): String {
        return name.lowercase(getDefault())

    }
}