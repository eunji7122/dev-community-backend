package com.study.devcommunitybackend.domain.post.repository

import com.study.devcommunitybackend.domain.post.data.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {

    fun findAllByBoardId(boardId: Long) : List<Post>
}