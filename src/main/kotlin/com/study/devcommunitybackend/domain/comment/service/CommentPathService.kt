package com.study.devcommunitybackend.domain.comment.service

import com.study.devcommunitybackend.domain.comment.data.dto.CommentPathRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentPathResponseDto
import com.study.devcommunitybackend.domain.comment.repository.CommentPathRepository
import com.study.devcommunitybackend.domain.comment.repository.CommentRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class CommentPathService (
        private val commentPathRepository: CommentPathRepository,
        private val commentRepository: CommentRepository
) {

    fun getAllSubCommentByMainComment(mainCommentId: Long) : List<Long> {
        return commentPathRepository.findSubCommentIdAllByMainCommentId(mainCommentId)
    }

    fun createCommentPath(commentPathRequestDto: CommentPathRequestDto) : CommentPathResponseDto {
        val foundMainComment = commentRepository.findByIdOrNull(commentPathRequestDto.mainCommentId)
        val foundSubComment = commentRepository.findByIdOrNull(commentPathRequestDto.subCommentId)
        return commentPathRepository.save(commentPathRequestDto.toEntity(foundMainComment!!, foundSubComment!!)).toDto()
    }

    fun deleteCommentPath(commentPathRequestDto: CommentPathRequestDto) {
        val foundMainComment = commentRepository.findByIdOrNull(commentPathRequestDto.mainCommentId)
        val foundSubComment = commentRepository.findByIdOrNull(commentPathRequestDto.subCommentId)

        val foundCommentPath = commentPathRepository.findByMainCommentIdAndSubCommentId(foundMainComment?.id!!, foundSubComment?.id!!)
        commentPathRepository.delete(foundCommentPath)
    }
}