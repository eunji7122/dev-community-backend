package com.study.devcommunitybackend.domain.point.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.post.data.entity.Post
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.point.data.dto.PointLogResponseDto
import jakarta.persistence.*

@Entity
@Table
class PointLog (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        val member: Member,

        @ManyToOne
        @JoinColumn(name = "post_id", nullable = true)
        val post: Post,

        @ManyToOne
        @JoinColumn(name = "comment_id", nullable = true)
        val comment: Comment,

        @Column(nullable = false)
        val amount: Int,

        @Column(nullable = false)
        val description: String
) : BaseEntity() {

        fun toDto() : PointLogResponseDto = PointLogResponseDto(id!!, member.toDto(), post.toDto(), comment.toDto(), amount, description)

}