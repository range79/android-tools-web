package com.range.adb.doman.entity

import com.range.adb.enums.AdbDeviceStatus
import jakarta.persistence.*

@Entity
@Table(name = "device")
data class AdbDevice (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var serial: String? = null,
    var codename: String? = null,
    var unlocked: Boolean = false,
    var isAbDevice: Boolean = false,
    @Enumerated(EnumType.STRING)
    var status: AdbDeviceStatus = AdbDeviceStatus.Disconnected,
)


