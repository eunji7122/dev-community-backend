package com.study.devcommunitybackend.domain.comment.data.dto

import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto
import com.study.devcommunitybackend.domain.post.data.dto.PostResponseDto

data class CommentResponseDto(
        val id: Long,
        val contents: String,
        val member: MemberResponseDto,
        val post: PostResponseDto
)
