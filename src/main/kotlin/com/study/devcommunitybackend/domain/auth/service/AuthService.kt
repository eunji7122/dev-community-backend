package com.study.devcommunitybackend.domain.auth.service

import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import com.study.devcommunitybackend.common.data.dto.TokenDto
import com.study.devcommunitybackend.domain.auth.data.dto.RefreshTokenRequestDto
import com.study.devcommunitybackend.domain.auth.data.entity.RefreshToken
import com.study.devcommunitybackend.domain.auth.repository.RefreshTokenRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    val jwtTokenProvider: JwtTokenProvider,
    val refreshTokenRepository: RefreshTokenRepository,
){

    // 토큰 만료 시 재발급
    fun reissueToken(refreshTokenRequestDto: RefreshTokenRequestDto): TokenDto {
        val dbRefreshToken = refreshTokenRepository.findByToken(refreshTokenRequestDto.token) ?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")

        if (refreshTokenRequestDto.token != dbRefreshToken.token) {
            throw IllegalArgumentException()
        }

        val authentication = jwtTokenProvider.getAuthentication(refreshTokenRequestDto.token)

        val newRefreshToken = jwtTokenProvider.createRefreshToken(authentication)

        val foundDbRefreshToken = refreshTokenRepository.findByUsername(authentication.name)
        if (foundDbRefreshToken != null) {
            foundDbRefreshToken.token = newRefreshToken
            refreshTokenRepository.save(foundDbRefreshToken)
        } else {
            refreshTokenRepository.save(RefreshToken(null, authentication.name, newRefreshToken))
        }

        return TokenDto(jwtTokenProvider.createToken(authentication), newRefreshToken)
    }

//    fun createAuthToken(loginMemberRequestDto: LoginMemberRequestDto): AuthTokenResponseDto {
//        val user = userRepository.findByEmail(loginMemberRequestDto.email) ?: throw RuntimeException()
//
//        if (!bCryptPasswordEncoder.matches(loginMemberRequestDto.password, user.password))
//            throw RuntimeException()
//
//        val token = jwtTokenProvider.createToken(user.loginId, user.role.toString())
//        return AuthTokenResponseDto.fromModel(token.accessToken, token.refreshToken)
//    }

}