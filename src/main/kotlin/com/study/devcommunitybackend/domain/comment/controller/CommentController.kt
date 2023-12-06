package com.study.devcommunitybackend.domain.comment.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.common.status.ResultCode
import com.study.devcommunitybackend.domain.comment.data.dto.CommentRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentResponseDto
import com.study.devcommunitybackend.domain.comment.service.CommentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController (
        private val commentService: CommentService
) {

    @PostMapping()
    fun createComment(@RequestBody commentRequestDto: CommentRequestDto) : BaseResponseDto<CommentResponseDto> {
        val createdComment = commentService.createComment(commentRequestDto)
        return BaseResponseDto(data = createdComment)
    }

    @GetMapping()
    fun getAllCommentByPostId(@RequestBody commentRequestDto: CommentRequestDto) : BaseResponseDto<ArrayList<Any>> {
        val comments = commentService.getAllCommentByPostId(commentRequestDto)
        return BaseResponseDto(data = comments)
    }

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): BaseResponseDto<MutableMap<String, Any>> {
        val comment = commentService.getComment(id)
        val message = if (comment == null) "삭제되었거나 존재하지 않는 댓글입니다." else ResultCode.SUCCESS.msg
        return BaseResponseDto(data = comment, message = message)
    }

    @PutMapping()
    fun updateComment(@RequestBody commentRequestDto: CommentRequestDto) : BaseResponseDto<CommentResponseDto> {
        val updatedComment = commentService.updateComment(commentRequestDto)
        return BaseResponseDto(data = updatedComment)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long) {
        commentService.deleteComment(id)
    }

}