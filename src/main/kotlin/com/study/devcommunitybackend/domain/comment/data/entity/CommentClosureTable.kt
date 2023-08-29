package com.study.devcommunitybackend.domain.comment.data.entity

import jakarta.persistence.*

@Entity
@Table
class CommentClosureTable (
    @ManyToOne @JoinColumn(name = "main_comment_id", nullable = false) val mainComment: Comment,
    @ManyToOne @JoinColumn(name = "sub_comment_id", nullable = false) val subComment: Comment,
    @Column(nullable = false) val depth: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}