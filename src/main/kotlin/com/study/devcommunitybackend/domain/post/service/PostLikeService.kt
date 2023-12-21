package com.study.devcommunitybackend.domain.post.service

import com.study.devcommunitybackend.common.data.dto.CustomUser
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.post.data.dto.PostLikeRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.PostLikeResponseDto
import com.study.devcommunitybackend.domain.post.repository.PostLikeRepository
import com.study.devcommunitybackend.domain.post.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
@Transactional
class PostLikeService (
        private val postLikeRepository: PostLikeRepository,
        private val memberRepository: MemberRepository,
        private val postRepository: PostRepository
) {

    // 회원이 좋아요를 누른 포스트 모두 검색
    fun getAllPostLikeByMember(): List<PostLikeResponseDto>? {
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId

        return postLikeRepository.findAllByMemberId(userId).stream().map { it.toDto() }.toList()
    }

    // 특정 포스트의 좋아요 개수 가져오기
    fun getLikeCountByPost(postId: Long): List<PostLikeResponseDto>? {
        return postLikeRepository.findAllByPostId(postId).stream().map { it.toDto() }.toList()
    }

    fun addPostLike(postLikeRequestDto: PostLikeRequestDto): PostLikeResponseDto? {
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId
        val foundMember = memberRepository.findByIdOrNull(userId)
        val foundPost = postRepository.findByIdOrNull(postLikeRequestDto.postId)

        postLikeRepository.findByMemberIdAndPostId(userId, foundPost?.id!!)
                ?: return postLikeRepository.save(postLikeRequestDto.toEntity(foundPost, foundMember!!)).toDto()

        return null
    }

    fun removePostLike(postLikeRequestDto: PostLikeRequestDto) {
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId
        val foundPost = postRepository.findByIdOrNull(postLikeRequestDto.postId)

        val foundPostLike = postLikeRepository.findByMemberIdAndPostId(userId, foundPost?.id!!)
        if (foundPostLike != null) {
            postLikeRepository.delete(foundPostLike)
        }
    }

}