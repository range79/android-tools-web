package com.range.backup.service.impl

import com.range.backup.domain.entity.FileEntity
import com.range.backup.domain.entity.FileSaveType
import com.range.backup.domain.repository.FileRepository
import com.range.backup.dto.DropBoxFileSaveRequest
import com.range.backup.dto.FileSaveResponse
import com.range.backup.mapper.FileMapper
import com.range.backup.service.FileSaveService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Service
class FileSaveServiceImpl(
    private val fileRepository: FileRepository,
    private val fileMapper: FileMapper
) : FileSaveService {

    @Value("\${save.path}")
    private lateinit var saveLocation: String

    override fun saveFileToLocal(multipartFile: MultipartFile): FileSaveResponse {
        val originalFileName = multipartFile.originalFilename
            ?: throw IllegalArgumentException("File name is null")

        val safeFileName = Paths.get(originalFileName).fileName.toString()

        val directory = Paths.get(saveLocation)
        if (!Files.exists(directory)) {
            Files.createDirectories(directory)
        }

        val targetPath = resolveUniqueFileName(directory, safeFileName)

        try {
            multipartFile.transferTo(targetPath.toFile())
        } catch (e: Exception) {
            throw RuntimeException("File could not be saved: ${e.message}", e)
        }

        val fileEntity = FileEntity(
            id = null,
            name = targetPath.fileName.toString(),
            size = multipartFile.size,
            path = targetPath.toAbsolutePath().toString(),
            fileSaveType = FileSaveType.LOCAL
        )

        val saved = fileRepository.save(fileEntity)
        return fileMapper.toResponse(saved)
    }

    override fun saveToDropBox(dropBoxFileSaveRequest: DropBoxFileSaveRequest): FileSaveResponse {
        val fileEntity = FileEntity(
            id = null,
            name = dropBoxFileSaveRequest.name,
            path = dropBoxFileSaveRequest.path,
            fileSaveType = FileSaveType.DROPBOX,
            size = dropBoxFileSaveRequest.size
        )

        val saved = fileRepository.save(fileEntity)
        return fileMapper.toResponse(saved)
    }


    private fun resolveUniqueFileName(directory: Path, originalFileName: String): Path {
        val dotIndex = originalFileName.lastIndexOf('.')
        val baseName = if (dotIndex != -1) originalFileName.substring(0, dotIndex) else originalFileName
        val extension = if (dotIndex != -1) originalFileName.substring(dotIndex) else ""

        var counter = 0
        var targetPath: Path

        do {
            val fileName = if (counter == 0) "$baseName$extension" else "$baseName ($counter)$extension"
            targetPath = directory.resolve(fileName)
            counter++
        } while (Files.exists(targetPath))

        return targetPath
    }
}
