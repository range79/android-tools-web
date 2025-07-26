package com.range.adb.util

import com.range.adb.domain.entity.AdbDevice
import com.range.adb.domain.repository.AdbDeviceRepository
import com.range.adb.enums.AdbDeviceStatus
import com.range.adb.exception.AdbDeviceNotFoundException
import com.range.adb.service.helper.AdbCommandHelper
import com.range.common.util.WrapperUtil
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CheckAdbDeviceTest {
    private lateinit var device: AdbDevice
    private lateinit var adbDevice: AdbDevice
    private lateinit var repository: AdbDeviceRepository
    private lateinit var wrapperUtil: WrapperUtil
    private lateinit var helper: AdbCommandHelper
    private lateinit var checkAdbDevice: CheckAdbDevice
    @BeforeEach
    fun setUp() {

        repository = mockk()
        wrapperUtil = mockk()
        checkAdbDevice = mockk()

        device = AdbDevice(
            id = 1,
            serial = "hello",
            codename = "3",
            androidVersion = "helel",
            status = AdbDeviceStatus.Connected,
        )


        helper = AdbCommandHelper(repository, checkAdbDevice, wrapperUtil)
    }

    @Test
    fun `getDeviceOrThrow should return device when found`() {

        every { repository.findById(1) } returns java.util.Optional.of(device)

        val result = helper.getDeviceOrThrow(1)

        assertNotNull(result)
        assertEquals(device.id, result.id)
    }

    @Test
    fun `getDeviceOrThrow should throw exception when device not found`() {
        every { repository.findById(999) } returns java.util.Optional.empty()

        assertThrows<AdbDeviceNotFoundException> {
            helper.getDeviceOrThrow(999)
        }
    }

    @Test
    fun `checkDeviceIsAlive should not throw when device is connected`() {
        every { checkAdbDevice.checkNow("hello") } returns AdbDeviceStatus.Connected

        helper.checkDeviceIsAlive("hello")
    }

    @Test
    fun `checkDeviceIsAlive should throw when device is not connected`() {
        every { checkAdbDevice.checkNow("hello") } returns AdbDeviceStatus.Disconnected

        assertThrows<FastbootDeviceNotFoundException> {
            helper.checkDeviceIsAlive("hello")
        }
    }



}