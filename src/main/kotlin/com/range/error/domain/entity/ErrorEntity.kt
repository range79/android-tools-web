package com.range.error.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "errors")
data class ErrorEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var errorCode: Int,
    var errorMessage: String,
    var timeStamp: LocalDateTime=LocalDateTime.now()
)