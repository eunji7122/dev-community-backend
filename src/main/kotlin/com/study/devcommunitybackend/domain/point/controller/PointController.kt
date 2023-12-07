package com.study.devcommunitybackend.domain.point.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.point.data.dto.PointRequestDto
import com.study.devcommunitybackend.domain.point.data.dto.PointResponseDto
import com.study.devcommunitybackend.domain.point.service.PointService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/points")
class PointController (
        private val pointService: PointService
) {

    @GetMapping("/{memberId}")
    fun getPoint(@PathVariable memberId : Long) : BaseResponseDto<PointResponseDto> {
        val point = pointService.getPoint(memberId)
        return BaseResponseDto(data = point)
    }

    @PostMapping()
    fun createPoint(@RequestBody pointRequestDto: PointRequestDto) : BaseResponseDto<PointResponseDto> {
        val createdPoint = pointService.createPoint(pointRequestDto)
        return BaseResponseDto(data = createdPoint)
    }

    @PutMapping()
    fun updatePoint(@RequestBody pointRequestDto: PointRequestDto) : BaseResponseDto<PointResponseDto> {
        val updatedPoint = pointService.updatePoint(pointRequestDto)
        return BaseResponseDto(data = updatedPoint)
    }

    @DeleteMapping("/{memberId}")
    fun deletePoint(@PathVariable memberId : Long) {
        pointService.deletePoint(memberId)
    }
}