package com.range.fastboot.service.helper


import com.range.common.util.WrapperUtil
import com.range.fastboot.domain.entity.FastbootDeviceInfo
import com.range.fastboot.domain.repository.FastbootDevicesInfoRepository
import com.range.fastboot.enums.FastbootDeviceStatus
import com.range.fastboot.exception.FastbootDeviceNotFoundException
import com.range.fastboot.util.CheckFastbootDevices
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
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
        repository = mock(FastbootDevicesInfoRepository::class.java)
        wrapperUtil = mock(WrapperUtil::class.java)
        checkFastbootDevices = mock(CheckFastbootDevices::class.java)
        device= FastbootDeviceInfo(
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

        whenever(repository.findById(1)).thenReturn(java.util.Optional.of(device))

        val result = helper.getDeviceOrThrow(1)

        assertNotNull(result)
        assertEquals(device.id, result.id)
    }

    @Test
    fun `getDeviceOrThrow should throw exception when device not found`() {

        whenever(repository.findById(999)).thenReturn(java.util.Optional.empty())

        assertThrows<FastbootDeviceNotFoundException> {
            helper.getDeviceOrThrow(999)
        }
    }

    @Test
    fun `checkDeviceIsAlive should not throw when device is connected`() {

        whenever(checkFastbootDevices.checkNow("hello")).thenReturn(FastbootDeviceStatus.Connected)


        helper.checkDeviceIsAlive("hello")
    }

    @Test
    fun `checkDeviceIsAlive should throw when device is not connected`() {

        whenever(checkFastbootDevices.checkNow("hello")).thenReturn(FastbootDeviceStatus.Disconnected)


        assertThrows<FastbootDeviceNotFoundException> {
            helper.checkDeviceIsAlive("hello")
        }
    }

    @Test
    fun `executeCommand should return correct response when command is successful`() {

        val mockOutput = "success"
        whenever(repository.findById(1)).thenReturn(java.util.Optional.of(device))
        whenever(checkFastbootDevices.checkNow("hello")).thenReturn(FastbootDeviceStatus.Connected)
        whenever(wrapperUtil.getFastbootOutput(eq("hello"), any())).thenReturn(mockOutput)

        val result = helper.executeCommand(1, "success", "someCommand", FastbootDeviceStatus.Disconnected)

        assertNotNull(result)
        assertEquals(device.serial, result.serial)
        assertEquals(FastbootDeviceStatus.Disconnected, result.fastbootDeviceStatus)
        verify(repository).save(device)
    }
    @Test
    fun `executeCommand should throw exception when output does not contain expected string`() {
        val mockOutput = "error"
        whenever(repository.findById(1)).thenReturn(java.util.Optional.of(device))
        whenever(checkFastbootDevices.checkNow("hello")).thenReturn(FastbootDeviceStatus.Connected)
        whenever(wrapperUtil.getFastbootOutput(eq("hello"), any())).thenReturn(mockOutput)

        assertThrows<FastbootDeviceNotFoundException> {
            helper.executeCommand(1, "success", "someCommand", null)
        }
    }


}