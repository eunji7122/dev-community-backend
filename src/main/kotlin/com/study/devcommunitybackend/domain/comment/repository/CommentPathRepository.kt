package com.study.devcommunitybackend.domain.comment.repository

import com.study.devcommunitybackend.domain.comment.data.entity.CommentPath
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CommentPathRepository : JpaRepository<CommentPath, Long> {

    @Query(value = "SELECT sub_comment_id FROM Comment_Path where main_comment_id = :mainCommentId", nativeQuery = true)
    fun findSubCommentIdAllByMainCommentId(@Param("mainCommentId") mainCommentId: Long) : List<Long>

    fun findByMainCommentIdAndSubCommentId(mainCommentId: Long, subCommentId: Long) : CommentPath

    fun findBySubCommentId(subCommentId: Long) : CommentPath?
}