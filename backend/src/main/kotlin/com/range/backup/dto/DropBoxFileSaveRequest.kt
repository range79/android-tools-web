package com.range.backup.dto

data class DropBoxFileSaveRequest (
    val name: String,
    val path: String,
    val size: Long
)