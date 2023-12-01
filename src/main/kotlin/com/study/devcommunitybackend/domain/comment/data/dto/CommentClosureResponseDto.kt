package com.study.devcommunitybackend.domain.comment.data.dto

data class CommentClosureResponseDto(
        val id: Long,
        val mainCommentResponseDto: CommentResponseDto,
        val subCommentResponseDto: CommentResponseDto
)
