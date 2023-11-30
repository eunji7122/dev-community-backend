package com.study.devcommunitybackend.domain.comment.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.comment.data.entity.CommentLike
import com.study.devcommunitybackend.domain.member.data.entity.Member
import jakarta.validation.constraints.NotBlank

data class CommentLikeRequestDto(
        val id: Long?,

        @field:NotBlank
        @JsonProperty("commentId")
        private val _commentId: Long?,

//        @field:NotBlank
//        @JsonProperty("memberId")
//        private val _memberId: Long?,
) {

    val commentId: Long
        get() = _commentId!!

//    val memberId: Long
//        get() = _memberId!!

    fun toEntity(comment: Comment, member: Member) : CommentLike = CommentLike(id, comment, member)
}
