package com.study.devcommunitybackend.domain.user.data.dto

data class CreateUserRequestDto(
    val email: String,
    val password: String,
    val name: String,
)
