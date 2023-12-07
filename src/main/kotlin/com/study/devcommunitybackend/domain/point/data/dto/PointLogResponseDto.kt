package com.study.devcommunitybackend.domain.point.data.dto

import com.study.devcommunitybackend.domain.comment.data.dto.CommentResponseDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto
import com.study.devcommunitybackend.domain.post.data.dto.PostResponseDto

data class PointLogResponseDto(
        val id: Long,
        val member: MemberResponseDto,
        val post: PostResponseDto,
        val comment: CommentResponseDto,
        val amount: Int,
        val description: String
)
