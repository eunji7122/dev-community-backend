package com.study.devcommunitybackend.domain.comment.data.dto

import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto

data class CommentLikeResponseDto(
        val id: Long,
        val commentResponseDto: CommentResponseDto,
        val memberResponseDto: MemberResponseDto
)
