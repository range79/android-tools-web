package com.range.common.util

import com.range.fastboot.enums.FastbootDeviceStatus
import kotlinx.coroutines.*
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Component
class CheckDevices(
    private val wrapperUtil: WrapperUtil
): CoroutineScope {
    private val scope = CoroutineScope(Dispatchers.Default)
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
}