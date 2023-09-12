package com.study.devcommunitybackend.domain.user.service

import com.study.devcommunitybackend.domain.auth.data.dto.AuthTokenResponseDto
import com.study.devcommunitybackend.domain.user.data.dto.CreateUserRequestDto
import com.study.devcommunitybackend.domain.user.data.dto.LoginUserRequestDto
import com.study.devcommunitybackend.domain.user.data.dto.UserResponseDto
import com.study.devcommunitybackend.domain.user.data.entity.RoleType
import com.study.devcommunitybackend.domain.user.data.entity.User
import com.study.devcommunitybackend.domain.user.repository.UserRepository
import com.study.devcommunitybackend.global.config.security.JwtTokenProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    val userRepository: UserRepository,
    val jwtTokenProvider: JwtTokenProvider,
    val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun createUser(createUserRequestDto: CreateUserRequestDto): UserResponseDto {
        if (userRepository.findByEmail(createUserRequestDto.email) != null) {
            throw RuntimeException()
        }

        val newUser = User(createUserRequestDto.email,
            bCryptPasswordEncoder.encode(createUserRequestDto.password),
            createUserRequestDto.name,
            RoleType.MEMBER,
            1000)
        val savedUser = userRepository.save(newUser)
        return UserResponseDto.fromModel(savedUser)
    }

    fun loginUser(loginUserRequestDto: LoginUserRequestDto): AuthTokenResponseDto {
        val user = userRepository.findByEmail(loginUserRequestDto.email) ?: throw RuntimeException()

        if (!bCryptPasswordEncoder.matches(loginUserRequestDto.password, user.password))
            throw RuntimeException()

        val token = jwtTokenProvider.createToken(user.email, user.role.toString())
        return AuthTokenResponseDto.fromModel(token.accessToken, token.refreshToken)
    }

}