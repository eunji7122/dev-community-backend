package com.study.devcommunitybackend.domain.comment.repository

import com.study.devcommunitybackend.domain.comment.data.entity.CommentClosureTable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentClosureRepository : JpaRepository<CommentClosureTable, Long> {

    fun findAllByMainCommentId(mainCommentId: Long) : List<CommentClosureTable>
}