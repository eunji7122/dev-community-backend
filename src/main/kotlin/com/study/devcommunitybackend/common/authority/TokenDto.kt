package com.study.devcommunitybackend.common.authority

data class TokenDto(
    val grantType: String,
    val accessToken: String,
)
