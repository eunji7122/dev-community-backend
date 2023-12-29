package com.study.devcommunitybackend.domain.post.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.post.data.entity.Post
import com.study.devcommunitybackend.domain.post.data.entity.SelectedPost
import jakarta.validation.constraints.NotBlank

data class SelectedPostRequestDto(
    val id: Long?,

    @field:NotBlank
    @JsonProperty("postId")
    private val _postId: Long?,

    @field:NotBlank
    @JsonProperty("selectedCommentId")
    private val _selectedCommentId: Long?,

    @field:NotBlank
    @JsonProperty("rewardPoint")
    private val _rewardPoint: Int?,
) {
    val postId: Long
        get() = _postId!!

    val selectedCommentId: Long
        get() = _selectedCommentId!!

    val rewardPoint: Int
        get() = _rewardPoint!!

    fun toEntity(post: Post, selectedComment: Comment): SelectedPost = SelectedPost(id, post, selectedComment, rewardPoint)
}
