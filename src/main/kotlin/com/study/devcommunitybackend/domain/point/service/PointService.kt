package com.study.devcommunitybackend.domain.point.service

import com.study.devcommunitybackend.common.data.dto.CustomUser
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.point.data.dto.PointRequestDto
import com.study.devcommunitybackend.domain.point.data.dto.PointResponseDto
import com.study.devcommunitybackend.domain.point.repository.PointRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
@Transactional
class PointService (
        private val pointRepository: PointRepository,
        private val memberRepository: MemberRepository,
        private val pointLogRepository: PointRepository
) {

    val defaultPointValue = 0;

    fun createPoint(pointRequestDto: PointRequestDto) : PointResponseDto? {
        val foundMember = memberRepository.findByIdOrNull(pointRequestDto.memberId)
        if (foundMember != null) {
            return pointRepository.save(pointRequestDto.toEntity(foundMember, this.defaultPointValue)).toDto()
        }
        return null
    }

    fun getPoint(memberId : Long) : PointResponseDto? {
        val foundMember = memberRepository.findByIdOrNull(memberId)
        return pointRepository.findByMemberId(foundMember!!.id!!)?.toDto()
    }

    fun updatePoint(pointRequestDto: PointRequestDto) : PointResponseDto? {
        val foundPoint = pointRepository.findByMemberId(pointRequestDto.memberId)

        if (foundPoint != null) {
            foundPoint.point = pointRequestDto.point

            pointRepository.save(foundPoint)
            return foundPoint.toDto()
        }
        return null
    }

    fun deletePoint(memberId: Long) {
        val foundPoint = pointRepository.findByMemberId(memberId)
        if (foundPoint != null) {
            pointRepository.delete(foundPoint)
        }
    }
}