package com.study.devcommunitybackend.data.entity.post

import com.study.devcommunitybackend.data.entity.comment.Comment
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table
class SelectedPost (
    post: Post,
    rewardPoint: Int,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post = post

    @Column(nullable = false)
    val rewardPoint: Int = rewardPoint

    @ManyToOne
    @JoinColumn(name = "selected_comment_id", nullable = true)
    val selectedCommentId: Comment? = null

    @Column(nullable = true)
    val selectedAt: LocalDateTime? = null

}