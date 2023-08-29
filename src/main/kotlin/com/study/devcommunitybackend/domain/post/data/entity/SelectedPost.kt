package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table
class SelectedPost (
    @ManyToOne @JoinColumn(name = "post_id", nullable = false) val post: Post,
    @Column(nullable = false) val rewardPoint: Int,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "selected_comment_id", nullable = true)
    val selectedCommentId: Comment? = null

    @Column(nullable = true)
    val selectedAt: LocalDateTime? = null

}