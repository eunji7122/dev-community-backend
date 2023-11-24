package com.study.devcommunitybackend.domain.member.data.entity

enum class Role (
        val key: String,
        val desc: String
) {
    ADMIN("ROLE_ADMIN", "관리자"),
    MEMBER("ROLE_USER", "사용자")
}