package com.study.devcommunitybackend.domain.comment.service

import com.study.devcommunitybackend.domain.comment.data.dto.CommentClosureRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentClosureResponseDto
import com.study.devcommunitybackend.domain.comment.repository.CommentClosureRepository
import com.study.devcommunitybackend.domain.comment.repository.CommentRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CommentClosureService (
        private val commentClosureRepository: CommentClosureRepository,
        private val commentRepository: CommentRepository
) {

    fun getAllSubCommentByMainComment(commentClosureRequestDto: CommentClosureRequestDto) : List<CommentClosureResponseDto> {
        return commentClosureRepository.findAllByMainCommentId(commentClosureRequestDto.mainCommentId).stream().map { it.toDto() }.toList()
    }
}