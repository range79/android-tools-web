package com.range.adb.domain.entity

import com.range.adb.enums.AdbDeviceStatus
import jakarta.persistence.*

@Entity
@Table(name = "device")
data class AdbDevice (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var serial: String,
    var codename: String? = null,
    @Enumerated(EnumType.STRING)
    var status: AdbDeviceStatus = AdbDeviceStatus.Disconnected,
)


