package com.study.devcommunitybackend.domain.auth.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.common.data.dto.TokenDto
import com.study.devcommunitybackend.domain.auth.data.dto.RefreshTokenRequestDto
import com.study.devcommunitybackend.domain.auth.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (
    val authService: AuthService
) {

    /**
     * 토큰 재발급
     */
    @PostMapping("/reissue")
    fun reissue(@RequestBody refreshTokenRequestDto: RefreshTokenRequestDto): BaseResponseDto<TokenDto> {
        val tokenInfo = authService.reissueToken(refreshTokenRequestDto)
        return BaseResponseDto(data = tokenInfo)
    }

}