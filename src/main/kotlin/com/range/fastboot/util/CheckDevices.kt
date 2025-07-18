package com.range.fastboot.util

import com.range.fastboot.enums.DeviceStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext
@Component
class CheckDevices(
    private val wrapperUtil: WrapperUtil, override val coroutineContext: CoroutineContext
): CoroutineScope {
    fun startChecking(deviceId: String, onStatusChange: (DeviceStatus) -> Unit) {
        launch {
            var lastStatus: DeviceStatus? = null
            while (isActive) {
                val output = wrapperUtil.getFastbootOutput(null, "devices")
                val isConnected = output.contains(deviceId)
                val currentStatus = if (isConnected) DeviceStatus.Connected else DeviceStatus.Disconnected

                if (currentStatus != lastStatus) {
                    onStatusChange(currentStatus)
                    lastStatus = currentStatus
                }
                delay(1000)
            }
        }
    }
}