package com.study.devcommunitybackend.domain.auth.service

import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService (
    val userRepository: MemberRepository,
    val jwtTokenProvider: JwtTokenProvider,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
){

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