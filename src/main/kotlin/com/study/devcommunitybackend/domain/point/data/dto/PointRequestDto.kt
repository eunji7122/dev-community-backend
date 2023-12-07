package com.study.devcommunitybackend.domain.point.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.point.data.entity.Point
import com.study.devcommunitybackend.domain.member.data.entity.Member
import jakarta.validation.constraints.NotBlank

data class PointRequestDto(
        var id: Long?,

        @field:NotBlank
        @JsonProperty("memberId")
        private val _memberId: Long?,

        @field:NotBlank
        @JsonProperty("point")
        private val _point: Int?,
) {
        val memberId: Long
            get() = _memberId!!

        val point: Int
            get() = _point!!
    fun toEntity(member: Member, point: Int) : Point = Point(id, member, point)
}
