package com.study.devcommunitybackend.domain.auth.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.auth.data.entity.RefreshToken
import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequestDto(
    var id: Long?,

    @field:NotBlank
    @JsonProperty("username")
    private val _username: String?,

    @field:NotBlank
    @JsonProperty("token")
    private val _token: String?,
) {
    val username: String
        get() = _username!!

    val token: String
        get() = _token!!

    fun toEntity(): RefreshToken = RefreshToken(id, username, token)

}
