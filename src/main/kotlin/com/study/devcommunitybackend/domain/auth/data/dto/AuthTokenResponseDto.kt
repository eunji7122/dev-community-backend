package com.study.devcommunitybackend.domain.auth.data.dto

data class AuthTokenResponseDto(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun fromModel(accessToken: String, refreshToken: String): AuthTokenResponseDto {
            return AuthTokenResponseDto(accessToken, refreshToken)
        }
    }
}
