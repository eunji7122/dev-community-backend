package com.study.devcommunitybackend.domain.post.service

import com.study.devcommunitybackend.domain.post.data.dto.PostTagRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.PostTagResponseDto
import com.study.devcommunitybackend.domain.post.repository.PostRepository
import com.study.devcommunitybackend.domain.post.repository.PostTagRepository
import com.study.devcommunitybackend.domain.post.repository.TagRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class PostTagService (
        private val postTagRepository: PostTagRepository,
        private val tagRepository: TagRepository,
        private val postRepository: PostRepository
) {

    // 특정 게시글에 등록된 모든 태그 가져오기
    fun getAllTagByPost(postId: Long): List<PostTagResponseDto>? {
        return postTagRepository.findAllByPostId(postId).stream().map { it.toDto() }.toList()
    }

    // 특정 게시글에 태그 등록하기
    fun createTagInPost(postTagRequestDto: PostTagRequestDto): PostTagResponseDto? {
        val foundTag = tagRepository.findByIdOrNull(postTagRequestDto.tagId)
        val foundPostTag = postTagRepository.findByPostIdAndTagId(postTagRequestDto.postId, postTagRequestDto.tagId)
        val foundPost = postRepository.findByIdOrNull(postTagRequestDto.postId)

        if (foundPostTag != null || foundTag == null || foundPost == null) {
            return null
        }

        return postTagRepository.save(postTagRequestDto.toEntity(foundPost, foundTag)).toDto()
    }

    // 특정 게시글에 등록된 태그 삭제하기
    fun deleteTagInPost(id: Long) {
        val foundPostTag = postTagRepository.findByIdOrNull(id)
        if (foundPostTag != null) {
            postTagRepository.delete(foundPostTag)
        }
    }
}