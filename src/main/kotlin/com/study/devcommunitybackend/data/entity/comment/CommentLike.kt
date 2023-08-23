package com.study.devcommunitybackend.data.entity.comment

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class CommentLike (
    commentId: Long,
    userId: Long
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val commentId: Long = commentId

    @Column(nullable = false)
    val userId: Long = userId
}