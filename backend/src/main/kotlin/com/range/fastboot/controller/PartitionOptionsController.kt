package com.range.fastboot.controller

import com.range.fastboot.enums.PartitionOptions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.web.bind.annotation.RestController
import kotlin.enums.EnumEntries
@RequestMapping("partition-options")
@RestController()
class PartitionOptionsController {
    @GetMapping("/all")
    fun partitionOptions(): EnumEntries<PartitionOptions> {
    return PartitionOptions.entries
    }
}