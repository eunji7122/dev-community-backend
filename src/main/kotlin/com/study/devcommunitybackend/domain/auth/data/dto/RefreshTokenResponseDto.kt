package com.study.devcommunitybackend.domain.auth.data.dto

data class RefreshTokenResponseDto(
    val id: Long,
    val username: String,
    val token: String,
)
