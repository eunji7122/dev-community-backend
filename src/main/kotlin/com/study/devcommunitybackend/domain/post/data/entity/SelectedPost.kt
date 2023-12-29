package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.post.data.dto.SelectedPostResponseDto
import jakarta.persistence.*

@Entity
@Table
class SelectedPost (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    @ManyToOne
    @JoinColumn(name = "selected_comment_id", nullable = true)
    val selectedComment: Comment,

    @Column(nullable = false)
    val rewardPoint: Int,
) : BaseEntity() {

    fun toDto(): SelectedPostResponseDto = SelectedPostResponseDto(id!!, post.toDto(), selectedComment.toDto(), rewardPoint)

}