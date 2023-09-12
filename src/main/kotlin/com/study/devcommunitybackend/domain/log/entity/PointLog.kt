package com.study.devcommunitybackend.domain.log.entity

import com.study.devcommunitybackend.global.common.entity.BaseEntity
import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import com.study.devcommunitybackend.domain.post.data.entity.Post
import com.study.devcommunitybackend.domain.user.data.entity.User
import jakarta.persistence.*

@Entity
@Table
class PointLog (
    user: User,
    @Column(nullable = false) val amount: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val userId: User = user

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    val post: Post? = null

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    val comment: Comment? = null

}