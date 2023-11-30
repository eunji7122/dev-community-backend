package com.study.devcommunitybackend.domain.comment.repository

import com.study.devcommunitybackend.domain.comment.data.entity.CommentLike
import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeRepository : JpaRepository<CommentLike, Long> {

    fun findAllByMemberId(memberId: Long) : List<CommentLike>

    fun findByMemberIdAndCommentId(memberId: Long, commentId: Long) : CommentLike?

    fun findAllByCommentId(commentId: Long) : List<CommentLike>
}