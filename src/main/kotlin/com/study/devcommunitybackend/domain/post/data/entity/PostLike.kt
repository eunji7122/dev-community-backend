package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.domain.user.data.entity.User
import jakarta.persistence.*

@Entity
@Table
class PostLike (
    @ManyToOne @JoinColumn(name = "post_id", nullable = false) val post: Post,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) val user: User
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}