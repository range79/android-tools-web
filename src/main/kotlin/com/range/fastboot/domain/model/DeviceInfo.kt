package com.range.fastboot.domain.model

import com.range.fastboot.enums.DeviceStatus
import jakarta.persistence.*

@Table(name = "device")
@Entity
data class DeviceInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var serial: String? = null,
    var codename: String? = null,
    var unlocked: Boolean = false,
    var isAbDevice: Boolean = false,
    @Enumerated(EnumType.STRING)
    var deviceStatus: DeviceStatus? = null
)