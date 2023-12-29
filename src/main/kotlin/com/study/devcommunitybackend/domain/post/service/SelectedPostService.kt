package com.study.devcommunitybackend.domain.post.service

import com.study.devcommunitybackend.domain.comment.repository.CommentRepository
import com.study.devcommunitybackend.domain.post.data.dto.SelectedPostRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.SelectedPostResponseDto
import com.study.devcommunitybackend.domain.post.repository.PostRepository
import com.study.devcommunitybackend.domain.post.repository.SelectedPostRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class SelectedPostService (
    private val selectedPostRepository: SelectedPostRepository,
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
){

    // 게시글 채택
    fun addSelectedPost(selectedPostRequestDto: SelectedPostRequestDto): SelectedPostResponseDto? {
        val foundComment = commentRepository.findByIdOrNull(selectedPostRequestDto.selectedCommentId)
        val foundPost = postRepository.findByIdOrNull(selectedPostRequestDto.postId)
        return selectedPostRepository.save(selectedPostRequestDto.toEntity(foundPost!!, foundComment!!)).toDto()
    }

    // 채택된 게시글 모두 가져오기
    fun getAllSelectedPosts(): List<SelectedPostResponseDto>? {
        return selectedPostRepository.findAll().stream().map { it.toDto() }.toList()
    }

    // 채택 여부 확인
    fun checkPostSelectingStatus(postId: Long): Boolean {
        return selectedPostRepository.findByPostId(postId) != null
    }

    // 게시글 채택 취소
    fun cancelSelectedPost(id: Long) {
        val foundSelectedPost = selectedPostRepository.findByIdOrNull(id)
        if (foundSelectedPost != null) {
            selectedPostRepository.delete(foundSelectedPost)
        }
    }
}