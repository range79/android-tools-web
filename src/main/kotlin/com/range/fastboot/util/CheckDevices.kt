package com.range.fastboot.util

import com.range.common.util.WrapperUtil
import com.range.fastboot.enums.FastbootDeviceStatus
import kotlinx.coroutines.*
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Component
class CheckDevices(
    private val wrapperUtil: WrapperUtil
): CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Default

    fun startChecking(deviceId: String, onStatusChange: (FastbootDeviceStatus) -> Unit) {
        launch {
            var lastStatus: FastbootDeviceStatus? = null
            while (isActive) {
                val output = wrapperUtil.getFastbootOutput(null, "devices")
                val isConnected = output.contains(deviceId)
                val currentStatus = if (isConnected) FastbootDeviceStatus.Connected else FastbootDeviceStatus.Disconnected

                if (currentStatus != lastStatus) {
                    onStatusChange(currentStatus)
                    lastStatus = currentStatus
                }
                delay(1000)
            }
        }
    }
    fun checkNow(deviceId: String): FastbootDeviceStatus {
        val output = wrapperUtil.getFastbootOutput(null, "devices")
        val isConnected = output.contains(deviceId)
        val currentStatus = if (isConnected) FastbootDeviceStatus.Connected else FastbootDeviceStatus.Disconnected

        return currentStatus
    }
}