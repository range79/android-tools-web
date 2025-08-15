package com.range.firmware.service

import com.range.firmware.domain.model.Firmware
import com.range.firmware.domain.repository.FirmwareRepository
import com.range.firmware.enum.FirmwareType
import com.range.firmware.exception.FirmwareNotFoundException
import com.range.firmware.exception.PathNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FirmwareServiceImpl(
    private val firmwareRepository: FirmwareRepository,
): FirmwareService {
    @Value("\${app.buildmode}")
    var appDevMode: Boolean = false

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<Firmware> {
        return firmwareRepository.findAll(pageable)
    }
    @Transactional(readOnly = true)
    override fun findById(id: Long): Firmware {
        return firmwareRepository.findById(id).orElseThrow{
            FirmwareNotFoundException("rom not found on id $id")
        }

    }
    @Transactional
    override fun deleteById(id: Long) {
        return firmwareRepository.delete(findById(id))

    }
    @Transactional
    override fun saveRom(multipartFile: MultipartFile,firmwareType: FirmwareType) {
       val jarDir : File = if (appDevMode) {

           val classPaths = System.getProperty("java.class.path").split(File.pathSeparator)
           File(classPaths.first()).absoluteFile.parentFile
       } else {

           val jarPath = this::class.java.protectionDomain.codeSource.location.toURI().path
           val file = File(jarPath)
           if (file.isFile) {
               file.parentFile
           } else {
               file
           }
       }


        if (!jarDir.exists()) {
            throw PathNotFoundException("Path does not exist")
        }

        val originalFilename = multipartFile.originalFilename
            ?: throw IllegalArgumentException("File name not found")

        if (!originalFilename.lowercase().endsWith(".zip")) {
            throw IllegalArgumentException("File Not Found correct name")
        }

        var fileName = originalFilename
        var outFile = File(jarDir, fileName)
        var counter = 1


        while (outFile.exists()) {
            val nameWithoutExt = originalFilename.substringBeforeLast(".zip")
            fileName = "$nameWithoutExt($counter).zip"
            outFile = File(jarDir, fileName)
            counter++
        }

        multipartFile.inputStream.use { input ->
            outFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        val firmware = Firmware(
            id = null,
            name = fileName,
            filepath = outFile.absolutePath,
            firmwareType = firmwareType
        )
        firmwareRepository.save(firmware)
    }

    private fun find(id: Long): Firmware{
        return firmwareRepository.findById(id).orElseThrow{
            FirmwareNotFoundException("rom not found on id $id")
        }
    }
}