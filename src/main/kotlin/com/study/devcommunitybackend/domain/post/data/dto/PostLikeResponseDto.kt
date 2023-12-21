package com.study.devcommunitybackend.domain.post.data.dto

import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto

data class PostLikeResponseDto(
        val id: Long,
        val postResponseDto: PostResponseDto,
        val memberResponseDto: MemberResponseDto
)
