package com.study.devcommunitybackend.domain.auth.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.auth.data.dto.RefreshTokenResponseDto
import jakarta.persistence.*

@Entity
@Table
class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = 30, updatable = false)
    var username: String,

    @Column(nullable = false)
    var token: String,
) : BaseEntity() {

    fun toDto(): RefreshTokenResponseDto = RefreshTokenResponseDto(id!!, username, token)

}