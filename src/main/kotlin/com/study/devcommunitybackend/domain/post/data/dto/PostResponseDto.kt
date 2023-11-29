package com.study.devcommunitybackend.domain.post.data.dto

import com.study.devcommunitybackend.domain.board.data.dto.BoardResponseDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto

data class PostResponseDto(
        val id: Long,
        val title: String,
        val content: String,
        val board: BoardResponseDto,
        val member: MemberResponseDto
) {
}
