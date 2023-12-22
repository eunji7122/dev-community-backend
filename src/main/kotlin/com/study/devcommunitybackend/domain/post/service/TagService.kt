package com.study.devcommunitybackend.domain.post.service

import com.study.devcommunitybackend.domain.post.data.dto.TagRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.TagResponseDto
import com.study.devcommunitybackend.domain.post.repository.TagRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class TagService (
        private val tagRepository: TagRepository
){

    fun getTag(id: Long): TagResponseDto? {
        val foundTag = tagRepository.findByIdOrNull(id)
        if (foundTag != null) {
            return foundTag.toDto()
        }
        return null
    }

    fun getAllTag(): List<TagResponseDto>? {
        return tagRepository.findAll().stream().map { it.toDto() }.toList()
    }

    fun createTag(tagRequestDto: TagRequestDto): TagResponseDto? {
        val foundTag = tagRepository.findByName(tagRequestDto.name)
        if (foundTag != null) {
            return null
        }
        return tagRepository.save(tagRequestDto.toEntity()).toDto()
    }

    fun updateTag(tagRequestDto: TagRequestDto): TagResponseDto? {
        val foundTag = tagRepository.findByIdOrNull(tagRequestDto.id)

        if (foundTag != null) {
            foundTag.name = tagRequestDto.name
            return tagRepository.save(foundTag).toDto()
        }
        return null
    }

    fun deleteTag(id: Long) {
        val foundTag = tagRepository.findByIdOrNull(id)
        if (foundTag != null) {
            tagRepository.delete(foundTag)
        }
    }
}