package com.study.devcommunitybackend.domain.point.data.entity

import com.study.devcommunitybackend.domain.point.data.dto.PointResponseDto
import com.study.devcommunitybackend.domain.member.data.entity.Member
import jakarta.persistence.*

@Entity
@Table
class Point (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @OneToOne
        @JoinColumn(name = "user_id", nullable = false)
        val member: Member,

        @Column(nullable = false)
        var point: Int
) {
        fun toDto(): PointResponseDto = PointResponseDto(id!!, point)
}