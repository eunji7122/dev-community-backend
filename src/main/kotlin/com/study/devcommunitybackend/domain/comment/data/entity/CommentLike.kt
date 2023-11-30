package com.study.devcommunitybackend.domain.comment.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.comment.data.dto.CommentLikeResponseDto
import com.study.devcommunitybackend.domain.member.data.entity.Member
import jakarta.persistence.*

@Entity
@Table
class CommentLike (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "comment_id", nullable = false)
        val comment: Comment,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        val member: Member
) : BaseEntity() {

        fun toDto(): CommentLikeResponseDto = CommentLikeResponseDto(id!!, comment.toDto(), member.toDto())

}