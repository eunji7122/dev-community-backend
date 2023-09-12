package com.study.devcommunitybackend.domain.user.data.dto

import com.study.devcommunitybackend.domain.user.data.entity.RoleType
import com.study.devcommunitybackend.domain.user.data.entity.User

data class UserResponseDto(
    val id: Long,
    val email: String,
    val name: String,
    val role: RoleType
) {
    companion object {
        fun fromModel(user: User): UserResponseDto {
            return UserResponseDto(user.id, user.email, user.name, user.role)
        }
    }
}
