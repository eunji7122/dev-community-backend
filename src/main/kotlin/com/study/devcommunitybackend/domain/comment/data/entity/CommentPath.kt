package com.study.devcommunitybackend.domain.comment.data.entity

import com.study.devcommunitybackend.domain.comment.data.dto.CommentPathResponseDto
import jakarta.persistence.*

@Entity
@Table
class CommentPath (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "main_comment_id", nullable = false)
        val mainComment: Comment,

        @ManyToOne
        @JoinColumn(name = "sub_comment_id", nullable = false)
        val subComment: Comment,
) {

        fun toDto(): CommentPathResponseDto = CommentPathResponseDto(id!!, mainComment.toDto(), subComment.toDto())

}