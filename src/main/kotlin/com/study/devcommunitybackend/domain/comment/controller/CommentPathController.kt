package com.study.devcommunitybackend.domain.comment.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentPathRequestDto
import com.study.devcommunitybackend.domain.comment.data.dto.CommentPathResponseDto
import com.study.devcommunitybackend.domain.comment.service.CommentPathService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comment-path")
class CommentPathController (
        private val commentPathService: CommentPathService
) {

    @GetMapping("/{id}")
    fun getAllSubCommentByMainComment(@PathVariable id: Long) : BaseResponseDto<List<Long>> {
        return BaseResponseDto(data = commentPathService.getAllSubCommentByMainComment(id))
    }

    @PostMapping()
    fun createCommentPath(@RequestBody commentPathRequestDto: CommentPathRequestDto) : BaseResponseDto<CommentPathResponseDto> {
        val createdCommentPath = commentPathService.createCommentPath(commentPathRequestDto)
        return BaseResponseDto(data = createdCommentPath)
    }

    @DeleteMapping()
    fun deleteCommentPath(@RequestBody commentPathRequestDto: CommentPathRequestDto) {
        commentPathService.deleteCommentPath(commentPathRequestDto)
    }
}