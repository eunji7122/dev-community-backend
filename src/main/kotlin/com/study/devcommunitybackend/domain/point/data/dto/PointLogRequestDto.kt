package com.study.devcommunitybackend.domain.point.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.point.data.entity.PointLog
import com.study.devcommunitybackend.domain.post.data.entity.Post
import jakarta.validation.constraints.NotBlank

data class PointLogRequestDto(
        var id: Long?,

        @field:NotBlank
        @JsonProperty("memberId")
        private val _memberId: Long?,

        @JsonProperty("postId")
        private val _postId: Long?,

        @JsonProperty("commentId")
        private val _commentId: Long?,

        @field:NotBlank
        @JsonProperty("amount")
        private val _amount: Int?,

        @JsonProperty("description")
        private val _description: String?,
) {
        val memberId: Long
                get() = _memberId!!

        val postId: Long
                get() = _postId!!

        val commentId: Long
                get() = _commentId!!

        val amount: Int
                get() = _amount!!

        val description: String
                get() = _description!!

        fun toEntity(member: Member, post: Post, comment: Comment) : PointLog
        = PointLog(id, member, post, comment, amount, description)
}
