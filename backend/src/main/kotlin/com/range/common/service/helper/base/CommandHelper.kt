package com.range.common.service.helper.base

import org.springframework.transaction.annotation.Transactional

/**
 * Generic interface to help execute device-related commands.
 *
 * @param T The type returned from the database (e.g. FastbootDeviceInfo, AdbDevice, etc.)
 * @param Y The type returned after executing a command (e.g. FastbootResponseDto, AdbResponseDto, etc.)
 * @param X The status enum representing the current state of the device (e.g. AdbStatus, FastbootDeviceStatus, etc.)
 */
interface CommandHelper<T,Y,X> {


    @Transactional(readOnly = true)
    fun getDeviceOrThrow(id: Long):T
    fun checkDeviceIsAlive(serial: String)
        @Transactional
    fun executeCommand(id: Long,
                       expectedOutputContains: String?=null,
                       command: String,
                       updateStatus: X?=null): Y
}