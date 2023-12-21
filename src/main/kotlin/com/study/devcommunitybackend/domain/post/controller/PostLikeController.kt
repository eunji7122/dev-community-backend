package com.study.devcommunitybackend.domain.post.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.post.data.dto.PostLikeRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.PostLikeResponseDto
import com.study.devcommunitybackend.domain.post.service.PostLikeService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post-likes")
class PostLikeController (
        private val postLikeService: PostLikeService
) {

    @GetMapping()
    fun getAllPostLikeByMember(): BaseResponseDto<List<PostLikeResponseDto>>? {
        val postLikes = postLikeService.getAllPostLikeByMember()
        return BaseResponseDto(data = postLikes)
    }

    @GetMapping("/count/{id}")
    fun getPostLikeCountByPost(@PathVariable id: Long): BaseResponseDto<Map<String, Any>> {
        val postLikes = postLikeService.getLikeCountByPost(id)
        val map = mapOf<String, Any>(
                "count" to postLikes!!.size,
                "list" to postLikes
        )
        return BaseResponseDto(data = map)
    }

    @PostMapping()
    fun addPostLike(@RequestBody postLikeRequestDto: PostLikeRequestDto): BaseResponseDto<PostLikeResponseDto>? {
        val postLike = postLikeService.addPostLike(postLikeRequestDto)

        return if (postLike == null) {
            BaseResponseDto(message = "이미 좋아요를 누른 게시글입니다.")
        } else {
            BaseResponseDto(data = postLike)
        }
    }

    @DeleteMapping()
    fun removePostLike(@RequestBody postLikeRequestDto: PostLikeRequestDto) {
        postLikeService.removePostLike(postLikeRequestDto)
    }
}