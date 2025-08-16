package com.range.fastboot.util

import com.range.common.util.WrapperUtil
import com.range.fastboot.enums.FastbootDeviceStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Component
class CheckFastbootDevices(
    private val wrapperUtil: WrapperUtil
): CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Default
    fun checkNow(deviceId: String): FastbootDeviceStatus {
        val output = wrapperUtil.getFastbootOutput(null, "devices")
        val isConnected = output.contains(deviceId)
        val currentStatus = if (isConnected) FastbootDeviceStatus.Connected else FastbootDeviceStatus.Disconnected

        return currentStatus
    }
}