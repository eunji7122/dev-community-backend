package com.study.devcommunitybackend.domain.post.data.dto

data class PostTagResponseDto(
        val id: Long,
        val post: PostResponseDto,
        val tat: TagResponseDto
)
