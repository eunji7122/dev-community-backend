package com.study.devcommunitybackend.data.entity.comment

import jakarta.persistence.*

@Entity
@Table
class CommentClosureTable (
    mainCommentId: Long,
    subCommentId: Long,
    depth: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val mainCommentId: Long = mainCommentId

    @Column(nullable = false)
    val subCommentId: Long = subCommentId

    @Column(nullable = false)
    val depth: Int = depth

}