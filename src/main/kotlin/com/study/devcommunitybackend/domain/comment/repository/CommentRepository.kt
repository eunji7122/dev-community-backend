package com.study.devcommunitybackend.domain.comment.repository

import com.study.devcommunitybackend.domain.comment.data.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findAllByPostId(postId: Long) : List<Comment>

    @Query(value = "SELECT c.* FROM comment AS c JOIN comment_path p ON c.id = p.sub_comment_id WHERE p.main_comment_id = :mainCommentId AND p.main_comment_id != p.sub_comment_id", nativeQuery = true)
    fun findSubComments(@Param("mainCommentId") mainCommentId: Long) : List<Comment>
}