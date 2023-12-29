package com.study.devcommunitybackend.domain.post.repository

import com.study.devcommunitybackend.domain.post.data.entity.SelectedPost
import org.springframework.data.jpa.repository.JpaRepository

interface SelectedPostRepository : JpaRepository<SelectedPost, Long>{

    fun findByPostId(postId: Long): SelectedPost?
}