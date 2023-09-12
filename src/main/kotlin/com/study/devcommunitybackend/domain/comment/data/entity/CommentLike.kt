package com.study.devcommunitybackend.domain.comment.data.entity

import com.study.devcommunitybackend.global.common.entity.BaseEntity
import com.study.devcommunitybackend.domain.user.data.entity.User
import jakarta.persistence.*

@Entity
@Table
class CommentLike (
    @ManyToOne @JoinColumn(name = "post_id", nullable = false) val comment: Comment,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) val user: User
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}