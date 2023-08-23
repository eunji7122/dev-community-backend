package com.study.devcommunitybackend.data.entity.comment

import jakarta.persistence.*

@Entity
@Table
class CommentClosureTable (
    mainComment: Comment,
    subComment: Comment,
    depth: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "main_comment_id", nullable = false)
    val mainComment: Comment = mainComment

    @ManyToOne
    @JoinColumn(name = "sub_comment_id", nullable = false)
    val subComment: Comment = subComment

    @Column(nullable = false)
    val depth: Int = depth

}