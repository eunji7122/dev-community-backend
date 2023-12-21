package com.study.devcommunitybackend.domain.post.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.post.data.entity.Post
import com.study.devcommunitybackend.domain.post.data.entity.PostLike
import jakarta.validation.constraints.NotBlank

data class PostLikeRequestDto(
        val id: Long?,

        @field:NotBlank
        @JsonProperty("postId")
        private val _postId: Long?
) {
        val postId: Long
                get() = _postId!!

        fun toEntity(post: Post, member: Member): PostLike = PostLike(id, post, member)
}
