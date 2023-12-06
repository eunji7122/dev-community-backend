package com.study.devcommunitybackend.domain.comment.service

import com.study.devcommunitybackend.common.data.dto.CustomUser
import com.study.devcommunitybackend.domain.comment.data.dto.CommentRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentResponseDto
import com.study.devcommunitybackend.domain.comment.data.entity.CommentPath
import com.study.devcommunitybackend.domain.comment.repository.CommentPathRepository
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
        private val commentPathRepository: CommentPathRepository,
) {

    fun createComment(commentRequestDto: CommentRequestDto) : CommentResponseDto? {
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId
        val foundMember = memberRepository.findByIdOrNull(userId)
        val foundPost = postRepository.findByIdOrNull(commentRequestDto.postId)

        val createdComment = commentRepository.save(commentRequestDto.toEntity(foundMember!!, foundPost!!))

        if (commentRequestDto.mainCommentId != null) {
            val foundMainComment = commentRepository.findByIdOrNull(commentRequestDto.mainCommentId)
            if (foundMainComment != null) {
                // 부모 댓글이 있는 경우
                commentPathRepository.save(CommentPath(null, foundMainComment, createdComment))
            } else {
                // 부모 댓글이 없는 경우
                commentPathRepository.save(CommentPath(null, createdComment, createdComment))
            }
        } else {
            commentPathRepository.save(CommentPath(null, createdComment, createdComment))
        }

        return createdComment.toDto()
    }

    fun getAllCommentByPostId(commentRequestDto: CommentRequestDto) : ArrayList<Any> {
        val results = arrayListOf<Any>(mutableMapOf<String, Any>())

        val foundComments = commentRepository.findAllByPostId(commentRequestDto.postId)
        for (comment in foundComments) {
            val commentPath = commentPathRepository.findBySubCommentId(comment.id!!)
            if (commentPath != null && commentPath.mainComment != commentPath.subComment) {
                // 댓글이 자식 댓글이면 건너뛰기
                continue
            }
            val foundSubComments = commentRepository.findSubComments(comment.id!!)

            val commentMap = mutableMapOf<String, Any>()
            commentMap["mainComment"] = comment.toDto()
            commentMap["subComments"] = foundSubComments.stream().map { it.toDto() }.toList()

            results.add(commentMap)
        }
        return results
    }

    fun getComment(id: Long) : MutableMap<String, Any>? {
        val foundComment = commentRepository.findByIdOrNull(id)
        if (foundComment != null) {
            if (foundComment.deletedAt == null) {
                val foundSubComments = commentRepository.findSubComments(id)

                val resultMap = mutableMapOf<String, Any>()
                resultMap["mainComment"] = foundComment.toDto()
                resultMap["subComments"] = foundSubComments.stream().map { it.toDto() }.toList()
                return resultMap
            }
        }
        return null
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