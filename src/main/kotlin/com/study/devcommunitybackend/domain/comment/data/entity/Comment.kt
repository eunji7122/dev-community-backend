package com.study.devcommunitybackend.domain.comment.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.comment.data.dto.CommentResponseDto
import com.study.devcommunitybackend.domain.post.data.entity.Post
import com.study.devcommunitybackend.domain.member.data.entity.Member
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE comment SET deleted_at = NOW() WHERE id = ?")
class Comment (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false, columnDefinition = "TEXT")
        var contents: String,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        val member: Member,

        @ManyToOne
        @JoinColumn(name = "post_id", nullable = false)
        val post: Post,
) : BaseEntity() {

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null

    fun toDto(): CommentResponseDto = CommentResponseDto(id!!, contents)

}