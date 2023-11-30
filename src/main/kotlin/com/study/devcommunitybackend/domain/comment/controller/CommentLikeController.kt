package com.study.devcommunitybackend.domain.comment.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentLikeRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentLikeResponseDto
import com.study.devcommunitybackend.domain.comment.service.CommentLikeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comment-likes")
class CommentLikeController (
        private val commentLikeService: CommentLikeService
) {

    @GetMapping()
    fun getAllCommentLikeByMember() : BaseResponseDto<List<CommentLikeResponseDto>>? {
        val commentLikes = commentLikeService.getAllCommentLikeByMember()
        return BaseResponseDto(data = commentLikes)
    }

    @GetMapping("/count/{id}")
    fun getCommentLikeCountByComment(@PathVariable id: Long) : BaseResponseDto<Map<String, Any>> {
        val commentLikes = commentLikeService.getLikeCountByComment(id)
        val map = mapOf<String, Any>("count" to commentLikes!!.size, "list" to commentLikes)
        return BaseResponseDto(data = map)
    }

    @PostMapping()
    fun addCommentLike(@RequestBody commentLikeRequestDto: CommentLikeRequestDto) : BaseResponseDto<CommentLikeResponseDto>? {
        val commentLike = commentLikeService.addCommentLike(commentLikeRequestDto)

        return if (commentLike == null) {
            BaseResponseDto(message = "이미 좋아요를 누른 댓글입니다.")
        } else {
            BaseResponseDto(data = commentLike)
        }
    }

    @DeleteMapping("/{id}")
    fun removeCommentLike(@PathVariable id: Long) {
        commentLikeService.removeCommentLike(id)
    }

}