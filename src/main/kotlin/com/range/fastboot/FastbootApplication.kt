package com.range.fastboot

import com.range.fastboot.domain.model.DeviceInfo
import com.range.fastboot.domain.repository.DeviceInfoRepository
import com.range.fastboot.service.save.impl.DeviceDataServiceImpl
import com.range.fastboot.util.WrapperUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FastbootApplication

fun main(args: Array<String>) {

    runApplication<FastbootApplication>(*args)

}
