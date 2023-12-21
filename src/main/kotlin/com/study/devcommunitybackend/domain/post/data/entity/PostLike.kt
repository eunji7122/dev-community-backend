package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.post.data.dto.PostLikeResponseDto
import jakarta.persistence.*

@Entity
@Table
class PostLike (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "post_id", nullable = false)
        val post: Post,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        val member: Member
) {
        fun toDto(): PostLikeResponseDto = PostLikeResponseDto(id!!, post.toDto(), member.toDto())
}