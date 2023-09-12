package com.study.devcommunitybackend.domain.auth.data.dto

data class AuthTokenRequestDto(
    val grantType: String,
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpireDate: Long
) {
    companion object {
        fun fromModel(grantType: String, accessToken: String, refreshToken: String, accessTokenExpireDate: Long): AuthTokenRequestDto {
            return AuthTokenRequestDto(grantType, accessToken, refreshToken, accessTokenExpireDate)
        }
    }
}

