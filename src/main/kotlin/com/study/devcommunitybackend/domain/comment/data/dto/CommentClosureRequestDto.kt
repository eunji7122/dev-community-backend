package com.study.devcommunitybackend.domain.comment.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.comment.data.entity.CommentClosureTable
import jakarta.validation.constraints.NotBlank

data class CommentClosureRequestDto(
        val id: Long?,

        @field:NotBlank
        @JsonProperty("mainCommentId")
        private val _mainCommentId: Long?,

        @field:NotBlank
        @JsonProperty("subCommentId")
        private val _subCommentId: Long?,
) {
    val mainCommentId: Long
        get() = _mainCommentId!!

    val subCommentId: Long
        get() = _subCommentId!!

    fun toEntity(mainComment: Comment, subComment: Comment) : CommentClosureTable = CommentClosureTable(id, mainComment, subComment)
}
