package com.range.fastboot.api

import com.range.fastboot.enums.PartitionOptions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/fastboot/flash/{deviceId}")

interface FastbootFlashingApi {

    @GetMapping("/{firmwareId}/{partitionOptions}")
    fun flash(@PathVariable deviceId: Long, @PathVariable firmwareId: UUID, @PathVariable partitionOptions: PartitionOptions)

}