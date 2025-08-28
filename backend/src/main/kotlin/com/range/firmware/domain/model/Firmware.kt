package com.range.firmware.domain.model

import com.range.firmware.enum.FirmwareType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "rom")
data class Firmware (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID?=null,
    var name: String,
    var filepath: String,
    var firmwareType: FirmwareType
)