package com.study.devcommunitybackend.domain.post.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.post.data.dto.PostTagRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.PostTagResponseDto
import com.study.devcommunitybackend.domain.post.service.PostTagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post-tags")
class PostTagController (
        private val postTagService: PostTagService
) {

    @GetMapping("/{postId}")
    fun getAllTagInPost(@PathVariable postId: Long): BaseResponseDto<List<PostTagResponseDto>> {
        val postTags = postTagService.getAllTagByPost(postId)
        return BaseResponseDto(data = postTags)
    }

    @PostMapping()
    fun createTagInPost(@RequestBody postTagRequestDto: PostTagRequestDto): BaseResponseDto<PostTagResponseDto> {
        val postTag = postTagService.createTagInPost(postTagRequestDto) ?: return BaseResponseDto(message = "이미 추가됐거나 등록할 수 없는 태그입니다.")
        return BaseResponseDto(data = postTag)
    }

    @DeleteMapping("/{id}")
    fun deleteTagInPost(@PathVariable id: Long) {
        postTagService.deleteTagInPost(id)
    }
}