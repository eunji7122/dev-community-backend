package com.study.devcommunitybackend.domain.user.controller

import com.study.devcommunitybackend.domain.auth.data.dto.AuthTokenResponseDto
import com.study.devcommunitybackend.domain.user.data.dto.CreateUserRequestDto
import com.study.devcommunitybackend.domain.user.data.dto.LoginUserRequestDto
import com.study.devcommunitybackend.domain.user.data.dto.UserResponseDto
import com.study.devcommunitybackend.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    val userService: UserService
) {

    @PostMapping("/create")
    fun createUser(@RequestBody createUserRequestDto: CreateUserRequestDto): ResponseEntity<UserResponseDto> {
        val userResponseDto = userService.createUser(createUserRequestDto)
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto)
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody loginUserRequestDto: LoginUserRequestDto): AuthTokenResponseDto {
        return userService.loginUser(loginUserRequestDto)
    }

}