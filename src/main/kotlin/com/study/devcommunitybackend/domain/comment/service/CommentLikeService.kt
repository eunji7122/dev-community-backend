package com.study.devcommunitybackend.domain.comment.service

import com.study.devcommunitybackend.common.data.dto.CustomUser
import com.study.devcommunitybackend.domain.comment.data.dto.CommentLikeRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentLikeResponseDto
import com.study.devcommunitybackend.domain.comment.repository.CommentLikeRepository
import com.study.devcommunitybackend.domain.comment.repository.CommentRepository
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
@Transactional
class CommentLikeService (
        private val commentLikeRepository: CommentLikeRepository,
        private val memberRepository: MemberRepository,
        private val commentRepository: CommentRepository
) {

    fun getAllCommentLikeByMember() : List<CommentLikeResponseDto>? {
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId

        return commentLikeRepository.findAllByMemberId(userId).stream().map { it.toDto() }.toList()
    }

    fun getLikeCountByComment(commentId: Long) : List<CommentLikeResponseDto>? {
        return commentLikeRepository.findAllByCommentId(commentId).stream().map { it.toDto() }.toList()
    }

    fun addCommentLike(commentLikeRequestDto: CommentLikeRequestDto) : CommentLikeResponseDto? {
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId
        val foundMember = memberRepository.findByIdOrNull(userId)
        val foundComment = commentRepository.findByIdOrNull(commentLikeRequestDto.commentId)

        commentLikeRepository.findByMemberIdAndCommentId(userId, foundComment?.id!!)
                ?: return commentLikeRepository.save(commentLikeRequestDto.toEntity(foundComment, foundMember!!)).toDto()

        return null
    }

    fun removeCommentLike(id: Long) {
        val foundCommentLike = commentLikeRepository.findByIdOrNull(id)
        if (foundCommentLike != null) {
            commentLikeRepository.delete(foundCommentLike)
        }
    }
}