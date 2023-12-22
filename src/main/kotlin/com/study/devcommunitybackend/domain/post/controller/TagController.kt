package com.study.devcommunitybackend.domain.post.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.post.data.dto.TagRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.TagResponseDto
import com.study.devcommunitybackend.domain.post.service.TagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tags")
class TagController (
        private val tagService: TagService
) {

    @GetMapping("/{id}")
    fun getTag(@PathVariable id: Long): BaseResponseDto<TagResponseDto> {
        val tag = tagService.getTag(id) ?: return BaseResponseDto(message = "존재하지 않는 태그입니다.")
        return BaseResponseDto(data = tag)
    }

    @GetMapping()
    fun getAllTag(): BaseResponseDto<List<TagResponseDto>> {
        val tags = tagService.getAllTag()
        return BaseResponseDto(data = tags)
    }

    @PostMapping()
    fun createTag(@RequestBody tagRequestDto: TagRequestDto): BaseResponseDto<TagResponseDto> {
        val tag = tagService.createTag(tagRequestDto)
        return BaseResponseDto(data = tag)
    }

    @PutMapping()
    fun updateTag(@RequestBody tagRequestDto: TagRequestDto): BaseResponseDto<TagResponseDto> {
        val tag = tagService.updateTag(tagRequestDto) ?: return BaseResponseDto(message = "존재하지 않는 태그입니다.")
        return BaseResponseDto(data = tag)
    }

    @DeleteMapping("/{id}")
    fun deleteTag(@PathVariable id: Long) {
        tagService.deleteTag(id)
    }

}