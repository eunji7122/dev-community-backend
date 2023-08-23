package com.study.devcommunitybackend.data.entity.post

import com.study.devcommunitybackend.data.entity.user.User
import jakarta.persistence.*

@Entity
@Table
class PostLike (
    post: Post,
    user: User
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post = post

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User = user

}