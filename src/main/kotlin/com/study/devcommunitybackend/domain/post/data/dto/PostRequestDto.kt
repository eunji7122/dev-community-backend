package com.study.devcommunitybackend.domain.post.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.board.data.entity.Board
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.post.data.entity.Post
import jakarta.validation.constraints.NotBlank

data class PostRequestDto(
        val id: Long?,

        @field:NotBlank
        @JsonProperty("title")
        private val _title: String?,

        @field:NotBlank
        @JsonProperty("content")
        private val _content: String?,

        @field:NotBlank
        @JsonProperty("boardId")
        private val _boardId: Long?,

        @field:NotBlank
        @JsonProperty("viewCount")
        private val _viewCount: Int?
) {
        val title: String
                get() = _title!!

        val content: String
                get() = _content!!

        val boardId: Long
                get() = _boardId!!

        val viewCount: Int
                get() = _viewCount!!

        fun toEntity(board: Board, member: Member): Post = Post(id, title, content, board, member, viewCount)
}
