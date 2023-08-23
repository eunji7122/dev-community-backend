package com.study.devcommunitybackend.data.entity.comment

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import com.study.devcommunitybackend.data.entity.user.User
import jakarta.persistence.*

@Entity
@Table
class CommentLike (
    comment: Comment,
    user: User
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val comment: Comment = comment

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User = user
}