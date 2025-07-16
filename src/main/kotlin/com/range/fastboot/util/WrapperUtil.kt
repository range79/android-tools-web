package com.range.fastboot.util

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class WrapperUtil {
    private val log = LoggerFactory.getLogger(WrapperUtil::class.java)
    private val os = System.getProperty("os.name").lowercase()
    private val fastbootCommand = if (os.contains("win")) "android-tools\\fastboot.exe" else "./android-tools/fastboot"
    private val adbCommand = if (os.contains("win")) "android-tools\\adb.exe" else "./android-tools/adb"

    private fun executeCommand(
        deviceId: String?,
        command: String,
        args: String
    ): String {
        val baseArgs = args.trim().split(" ")

        val finalArgs = if (!deviceId.isNullOrBlank()) {
            listOf("-s", deviceId) + baseArgs
        } else {
            baseArgs
        }

        val processBuilder = ProcessBuilder(command, *finalArgs.toTypedArray())
        val process = processBuilder.start()

        val output = process.inputStream.bufferedReader().readText()
        val error = process.errorStream.bufferedReader().readText()

        process.waitFor()

        return output.ifBlank { error }
    }

    fun getFastbootOutput(deviceId: String?, args: String): String {
        return executeCommand(deviceId, fastbootCommand, args)
    }

    fun getAdbOutput(deviceId: String?, args: String): String {
        return executeCommand(deviceId, adbCommand, args)
    }

    private fun executeCommandFlux(
        deviceId: String?,
        command: String,
        args: String
    ): Flux<String> {
        return Flux.create { sink ->
            val baseArgs = args.trim().split(" ")
            val finalArgs = if (!deviceId.isNullOrBlank()) {
                listOf("-s", deviceId) + baseArgs
            } else {
                baseArgs
            }

            val process = ProcessBuilder(command, *finalArgs.toTypedArray()).start()

            val inputReader = process.inputStream.bufferedReader()
            val errorReader = process.errorStream.bufferedReader()

            val inputThread = Thread {
                inputReader.forEachLine { line -> sink.next(line) }
            }
            val errorThread = Thread {
                errorReader.forEachLine { line ->
                    sink.next("ERR: $line")
                }
            }

            inputThread.start()
            errorThread.start()

            process.waitFor()

            inputThread.join()
            errorThread.join()

            sink.complete()
        }
    }

    fun getFastbootOutputFlux(deviceId: String?, args: String): Flux<String> {
        return executeCommandFlux(deviceId, fastbootCommand, args)
    }

    fun getAdbOutputFlux(deviceId: String?, args: String): Flux<String> {
        return executeCommandFlux(deviceId, adbCommand, args)
    }
}
