package com.range.fastboot.domain.repository

import com.range.fastboot.domain.entity.FastbootDeviceInfo
import org.springframework.data.jpa.repository.JpaRepository

interface FastbootDevicesInfoRepository : JpaRepository<FastbootDeviceInfo,Long>