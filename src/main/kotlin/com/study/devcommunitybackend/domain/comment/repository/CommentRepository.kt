package com.study.devcommunitybackend.domain.comment.repository

import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findAllByPostId(postId: Long) : List<Comment>
}