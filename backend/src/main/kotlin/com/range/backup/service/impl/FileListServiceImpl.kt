package com.range.backup.service.impl

import com.range.backup.domain.entity.FileSaveType
import com.range.backup.domain.repository.FileRepository
import com.range.backup.dto.FileSaveResponse
import com.range.backup.mapper.FileMapper
import com.range.backup.service.FileListService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FileListServiceImpl(
    private val fileRepository: FileRepository,
    private val fileMapper: FileMapper
): FileListService {
    override fun listFilesBySaveType(
        fileSaveType: FileSaveType,
        pageable: Pageable
    ): Page<FileSaveResponse> {
        return fileRepository.findAllByFileSaveType(fileSaveType,pageable).map{
            fileMapper.toResponse(it)
        }
    }

    override fun listAllFiles(pageable: Pageable): Page<FileSaveResponse> {
        return  fileRepository.findAll(pageable).map {
            fileMapper.toResponse(it)
        }
    }


}