package com.study.devcommunitybackend.domain.member.data.dto

data class MemberResponseDto(
    val id: Long,
    val loginId: String,
    val name: String,
    val email: String,
    val birthDate: String,
    val gender: String,
)
