package com.range.adb.util

import com.range.adb.enums.AdbDeviceStatus
import com.range.common.util.WrapperUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Component
class CheckAdbDevice(
    private val wrapperUtil: WrapperUtil
): CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Default
    fun checkNow(deviceId: String): AdbDeviceStatus {
        val output = wrapperUtil.getAdbOutput(null, "devices")

        return when {
            output.contains("Recovery")-> return AdbDeviceStatus.Recovery
            output.contains("sideload")->return AdbDeviceStatus.Sideload
            output.contains("connected") -> AdbDeviceStatus.Connected
            output.contains("unauthorized") -> AdbDeviceStatus.Unauthorized
            output.contains(deviceId) -> AdbDeviceStatus.Connected
            else -> {
                return AdbDeviceStatus.Disconnected
            }
        }
    }}