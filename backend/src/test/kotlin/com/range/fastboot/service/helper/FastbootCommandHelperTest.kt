package com.range.fastboot.service.helper

import com.range.common.util.WrapperUtil
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import com.range.fastboot.util.CheckFastbootDevices
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FastbootCommandHelperTest {

    private lateinit var repository: FastbootDevicesInfoRepository
    private lateinit var wrapperUtil: WrapperUtil
    private lateinit var checkFastbootDevices: CheckFastbootDevices
    private lateinit var helper: FastbootCommandHelper
    private lateinit var device: FastbootDeviceInfo

    @BeforeEach
    fun setUp() {

        repository = mockk()
        wrapperUtil = mockk()
        checkFastbootDevices = mockk()

        device = FastbootDeviceInfo(
            id = 1,
            serial = "hello",
            codename = "3",
            unlocked = false,
            isAbDevice = false,
            fastbootDeviceStatus = FastbootDeviceStatus.Connected
        )


        helper = FastbootCommandHelper(repository, wrapperUtil, checkFastbootDevices)
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

        assertThrows<FastbootDeviceNotFoundException> {
            helper.getDeviceOrThrow(999)
        }
    }

    @Test
    fun `checkDeviceIsAlive should not throw when device is connected`() {
        every { checkFastbootDevices.checkNow("hello") } returns FastbootDeviceStatus.Connected

        helper.checkDeviceIsAlive("hello")
    }

    @Test
    fun `checkDeviceIsAlive should throw when device is not connected`() {
        every { checkFastbootDevices.checkNow("hello") } returns FastbootDeviceStatus.Disconnected

        assertThrows<FastbootDeviceNotFoundException> {
            helper.checkDeviceIsAlive("hello")
        }
    }

    @Test
    fun `executeCommand should return correct response when command is successful`() {
        val mockOutput = "success"


        every { repository.findById(1) } returns java.util.Optional.of(device)
        every { checkFastbootDevices.checkNow("hello") } returns FastbootDeviceStatus.Connected
        every { wrapperUtil.getFastbootOutput("hello", any()) } returns mockOutput

        every { repository.save(any<FastbootDeviceInfo>()) } returns device

        val result = helper.executeCommand(1, "success", "someCommand", FastbootDeviceStatus.Disconnected)

        assertNotNull(result)
        assertEquals(device.serial, result.serial)
        assertEquals(FastbootDeviceStatus.Disconnected, result.fastbootDeviceStatus)


        verify { repository.save(device) }
    }
    @Test
    fun `executeCommand should throw exception when output does not contain expected string`() {
        val mockOutput = "error"
        every { repository.findById(1) } returns java.util.Optional.of(device)
        every { checkFastbootDevices.checkNow("hello") } returns FastbootDeviceStatus.Connected
        every { wrapperUtil.getFastbootOutput("hello", any()) } returns mockOutput

        assertThrows<FastbootDeviceNotFoundException> {
            helper.executeCommand(1, "success", "someCommand", null)
        }
    }
}
