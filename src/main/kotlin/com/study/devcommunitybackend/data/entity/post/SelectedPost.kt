package com.study.devcommunitybackend.data.entity.post

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table
class SelectedPost (
    postId: Long,
    rewardPoint: Int,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val postId: Long = postId

    @Column(nullable = false)
    val rewardPoint: Int = rewardPoint

    @Column(nullable = true)
    val selectedCommentId: Long? = null

    @Column(nullable = true)
    val selectedAt: LocalDateTime? = null

}