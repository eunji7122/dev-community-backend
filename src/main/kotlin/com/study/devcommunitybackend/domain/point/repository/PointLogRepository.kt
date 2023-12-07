package com.study.devcommunitybackend.domain.point.repository

import com.study.devcommunitybackend.domain.point.data.entity.PointLog
import org.springframework.data.jpa.repository.JpaRepository

interface PointLogRepository : JpaRepository<PointLog, Long> {

    fun findAllByMemberId(memberId: Long) : List<PointLog>
}