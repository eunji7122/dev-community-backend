package com.study.devcommunitybackend.domain.point.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.point.data.dto.PointLogRequestDto
import com.study.devcommunitybackend.domain.point.data.dto.PointLogResponseDto
import com.study.devcommunitybackend.domain.point.service.PointLogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/point-logs")
class PointLogController (
        private val pointLogService: PointLogService
) {

    @PostMapping()
    fun createPointLog(@RequestBody pointLogRequestDto: PointLogRequestDto) : BaseResponseDto<PointLogResponseDto> {
        val createdPointLog = pointLogService.createPointLog(pointLogRequestDto)
        return BaseResponseDto(data = createdPointLog)
    }

    @GetMapping("/{memberId}")
    fun getAllPointLogByMember(@PathVariable memberId: Long) : BaseResponseDto<List<PointLogResponseDto>> {
        val pointLogs = pointLogService.getAllPointLogByMember(memberId)
        return BaseResponseDto(data = pointLogs)
    }
}