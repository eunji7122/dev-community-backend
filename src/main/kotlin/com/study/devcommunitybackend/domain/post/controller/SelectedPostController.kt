package com.study.devcommunitybackend.domain.post.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.post.data.dto.SelectedPostRequestDto
import com.study.devcommunitybackend.domain.post.data.dto.SelectedPostResponseDto
import com.study.devcommunitybackend.domain.post.service.SelectedPostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/selected-posts")
class SelectedPostController (
    private val selectedPostService: SelectedPostService
) {

    @PostMapping()
    fun addSelectedPost(@RequestBody selectedPostRequestDto: SelectedPostRequestDto): BaseResponseDto<SelectedPostResponseDto> {
        val selectedPost = selectedPostService.addSelectedPost(selectedPostRequestDto)
        return BaseResponseDto(data = selectedPost)
    }

    @GetMapping()
    fun getAllSelectedPosts(): BaseResponseDto<List<SelectedPostResponseDto>> {
        val selectedPosts = selectedPostService.getAllSelectedPosts()
        return BaseResponseDto(data = selectedPosts)
    }

    @GetMapping("/{postId}")
    fun checkPostSelectingStatus(@PathVariable postId: Long): BaseResponseDto<Boolean> {
        val result = selectedPostService.checkPostSelectingStatus(postId)
        return BaseResponseDto(data = result)
    }

    @DeleteMapping("/{id}")
    fun cancelSelectedPost(@PathVariable id: Long) {
        selectedPostService.cancelSelectedPost(id)
    }
}