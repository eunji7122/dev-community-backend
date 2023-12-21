package com.study.devcommunitybackend.domain.post.repository

import com.study.devcommunitybackend.domain.post.data.entity.PostLike
import org.springframework.data.jpa.repository.JpaRepository

interface PostLikeRepository: JpaRepository<PostLike, Long> {

    fun findAllByMemberId(memberId: Long): List<PostLike>

    fun findAllByPostId(postId: Long): List<PostLike>

    fun findByMemberIdAndPostId(memberId: Long, commentId: Long): PostLike?
}