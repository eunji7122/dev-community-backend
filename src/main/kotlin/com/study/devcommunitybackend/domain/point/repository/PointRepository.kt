package com.study.devcommunitybackend.domain.point.repository

import com.study.devcommunitybackend.domain.point.data.entity.Point
import org.springframework.data.jpa.repository.JpaRepository

interface PointRepository : JpaRepository<Point, Long> {

    fun findByMemberId(memberId: Long) : Point?
}