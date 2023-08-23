package com.study.devcommunitybackend.data.entity.log

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import com.study.devcommunitybackend.data.entity.comment.Comment
import com.study.devcommunitybackend.data.entity.post.Post
import com.study.devcommunitybackend.data.entity.user.User
import jakarta.persistence.*

@Entity
@Table
class PointLog (
    user: User,
    amount: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val userId: User = user

    @Column(nullable = false)
    val amount: Int = amount

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    val post: Post? = null

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    val comment: Comment? = null

}