package com.study.devcommunitybackend.domain.post.data.dto

import com.study.devcommunitybackend.domain.comment.data.dto.CommentResponseDto

data class SelectedPostResponseDto(
    val id: Long,
    val post: PostResponseDto,
    val selectedComment: CommentResponseDto,
    val rewardPoint: Int,
)
