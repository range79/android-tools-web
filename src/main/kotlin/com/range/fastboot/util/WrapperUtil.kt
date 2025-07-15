package com.range.fastboot.util

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import kotlin.math.log

@Component
class WrapperUtil {
private val log = LoggerFactory.getLogger(WrapperUtil::class.java)
    private val os = System.getProperty("os.name").lowercase()
    private val fastbootCommand = if (os.contains("win")) "android-tools\\fastboot.exe" else "./android-tools/fastboot"
    private val adbCommand = if (os.contains("win")) "android-tools\\adb.exe" else "./android-tools/adb"


    private fun executeCommand(command: String, args: String): String {
        val processBuilder = ProcessBuilder(command, *args.trim().split(" ").toTypedArray())
        val process = processBuilder.start()
        val output = process.inputStream.bufferedReader().readText()
        val error = process.errorStream.bufferedReader().readText()

        process.waitFor()

        return output.ifBlank { error }
    }

    fun getFastbootOutput(args: String): String {
        return executeCommand(fastbootCommand, args)
    }

    fun getAdbOutput(args: String): String {
        return executeCommand(adbCommand, args)
    }

    // Canlı log stream (SSE için)
    private fun executeCommandFlux(command: String, args: String): Flux<String> {
        return Flux.create { sink ->
            val process = ProcessBuilder(command, *args.trim().split(" ").toTypedArray()).start()

            val inputReader = process.inputStream.bufferedReader()
            val errorReader = process.errorStream.bufferedReader()

            val inputThread = Thread {
                inputReader.forEachLine { line -> sink.next(line) }
            }
            val errorThread = Thread {
              log.error(errorReader.readLine())
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

    fun getFastbootOutputFlux(args: String): Flux<String> {
        return executeCommandFlux(fastbootCommand, args)
    }

    fun getAdbOutputFlux(args: String): Flux<String> {
        return executeCommandFlux(adbCommand, args)
    }
}
