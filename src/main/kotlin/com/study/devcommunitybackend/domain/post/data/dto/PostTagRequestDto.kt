package com.study.devcommunitybackend.domain.post.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.post.data.entity.Post
import com.study.devcommunitybackend.domain.post.data.entity.PostTag
import com.study.devcommunitybackend.domain.post.data.entity.Tag
import jakarta.validation.constraints.NotBlank

data class PostTagRequestDto(
        val id: Long?,

        @field:NotBlank
        @JsonProperty("postId")
        private val _postId: Long?,

        @field:NotBlank
        @JsonProperty("tagId")
        private val _tagId: Long?
) {
    val postId: Long
        get() = _postId!!

    val tagId: Long
        get() = _tagId!!

    fun toEntity(post: Post, tag: Tag): PostTag = PostTag(id, post, tag)
}
