package com.range.fastboot.domain.entity

import com.range.fastboot.enums.FastbootDeviceStatus
import jakarta.persistence.*

@Table(name = "device")
@Entity
data class FastbootDeviceInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var serial: String,
    var codename: String? = null,
    var unlocked: Boolean = false,
    var isAbDevice: Boolean = false,
    @Enumerated(EnumType.STRING)
    var fastbootDeviceStatus: FastbootDeviceStatus
)