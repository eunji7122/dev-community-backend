package com.study.devcommunitybackend.domain.comment.data.dto

data class CommentPathResponseDto(
        val id: Long,
        val mainCommentResponseDto: CommentResponseDto,
        val subCommentResponseDto: CommentResponseDto
)
