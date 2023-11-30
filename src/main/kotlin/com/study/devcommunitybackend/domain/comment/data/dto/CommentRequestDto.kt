package com.study.devcommunitybackend.domain.comment.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.post.data.entity.Post
import jakarta.validation.constraints.NotBlank

data class CommentRequestDto(
        val id: Long?,

        @field:NotBlank
        @JsonProperty("contents")
        private val _contents: String?,

//        @field:NotBlank
//        @JsonProperty("memberId")
//        private val _memberId: Long?,

        @field:NotBlank
        @JsonProperty("postId")
        private val _postId: Long?,
) {
        val contents: String
                get() = _contents!!

//        val memberId: Long
//                get() = _memberId!!

        val postId: Long
                get() = _postId!!

        fun toEntity(member: Member, post: Post): Comment = Comment(id, contents, member, post)
}
