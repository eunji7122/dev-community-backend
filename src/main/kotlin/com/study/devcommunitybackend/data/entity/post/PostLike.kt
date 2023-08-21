package com.study.devcommunitybackend.data.entity.post

import jakarta.persistence.*

@Entity
@Table
class PostLike (
    postId: Long,
    userId: Long
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val postId: Long = postId

    @Column(nullable = false)
    val userId: Long = userId

}