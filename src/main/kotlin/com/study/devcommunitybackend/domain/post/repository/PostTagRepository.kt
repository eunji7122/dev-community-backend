package com.study.devcommunitybackend.domain.post.repository

import com.study.devcommunitybackend.domain.post.data.entity.PostTag
import org.springframework.data.jpa.repository.JpaRepository

interface PostTagRepository: JpaRepository<PostTag, Long> {

    fun findAllByPostId(postId: Long): List<PostTag>

    fun findByPostIdAndTagId(postId: Long, tagId: Long): PostTag?
}