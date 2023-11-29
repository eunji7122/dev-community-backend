package com.study.devcommunitybackend.domain.post.service

import com.study.devcommunitybackend.common.data.dto.CustomUser
import com.study.devcommunitybackend.domain.board.repository.BoardRepository
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.post.data.dto.PostRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.PostResponseDto
import com.study.devcommunitybackend.domain.post.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
@Transactional
class PostService (
        private val postRepository: PostRepository,
        private val boardRepository: BoardRepository,
        private val memberRepository: MemberRepository,
){

    fun createPost(postRequestDto: PostRequestDto) : PostResponseDto? {
        val findBoard = boardRepository.findByIdOrNull(postRequestDto.boardId)
        val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .userId
        val findMember = memberRepository.findByIdOrNull(userId)
        return postRepository.save(postRequestDto.toEntity(findBoard!!, findMember!!)).toDto()
    }

    fun getPost(id: Long) : PostResponseDto? {
        val foundPost = postRepository.findByIdOrNull(id)
        if (foundPost?.deletedAt != null) {
            return null
        }
        return foundPost?.toDto()
    }

    fun getAllPostsByBoardId(postRequestDto: PostRequestDto) : List<PostResponseDto>? {
        return postRepository.findAllByBoardId(postRequestDto.boardId).stream().map {
            it.toDto()
        }.toList()
    }

    fun updatePost(postRequestDto: PostRequestDto) : PostResponseDto? {
        val foundPost = postRepository.findByIdOrNull(postRequestDto.id)

        if (foundPost != null) {
            foundPost.title = postRequestDto.title
            foundPost.content = postRequestDto.content

            postRepository.save(foundPost)
            return foundPost.toDto()
        }

        return null
    }

    fun deleteBoard(id: Long) {
        val foundPost = postRepository.findByIdOrNull(id)
        if (foundPost != null) {
            postRepository.delete(foundPost)
        }
    }

}