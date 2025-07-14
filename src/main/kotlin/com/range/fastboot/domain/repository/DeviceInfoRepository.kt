package com.range.fastboot.domain.repository

import com.range.fastboot.domain.model.DeviceInfo
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceInfoRepository : JpaRepository<DeviceInfo,Long>