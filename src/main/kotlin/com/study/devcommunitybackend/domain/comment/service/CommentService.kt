package com.study.devcommunitybackend.domain.comment.service

import com.study.devcommunitybackend.common.data.dto.CustomUser
import com.study.devcommunitybackend.domain.comment.data.dto.CommentRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentResponseDto
import com.study.devcommunitybackend.domain.comment.repository.CommentRepository
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.post.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
@Transactional
class CommentService (
        private val commentRepository: CommentRepository,
        private val postRepository: PostRepository,
        private val memberRepository: MemberRepository,
) {

    fun createComment(commentRequestDto: CommentRequestDto) : CommentResponseDto? {
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId
        val foundMember = memberRepository.findByIdOrNull(userId)

        val foundPost = postRepository.findByIdOrNull(commentRequestDto.postId)
        return commentRepository.save(commentRequestDto.toEntity(foundMember!!, foundPost!!)).toDto()
    }

    fun getAllCommentByPostId(commentRequestDto: CommentRequestDto) : List<CommentResponseDto>? {
        return commentRepository.findAllByPostId(commentRequestDto.postId).filter { it.deletedAt == null }.stream().map { it.toDto() }.toList()
    }

    fun getComment(id: Long) : CommentResponseDto? {
        val foundComment = commentRepository.findByIdOrNull(id)
        if (foundComment?.deletedAt != null) {
            return null
        }
        return foundComment?.toDto()
    }

    fun updateComment(commentRequestDto: CommentRequestDto) : CommentResponseDto? {
        val foundComment = commentRepository.findByIdOrNull(commentRequestDto.id)

        if (foundComment != null) {
            foundComment.contents = commentRequestDto.contents

            commentRepository.save(foundComment)
            return foundComment.toDto()
        }

        return null
    }

    fun deleteComment(id: Long) {
        val foundComment = commentRepository.findByIdOrNull(id)
        if (foundComment != null) {
            commentRepository.delete(foundComment)
        }
    }
}