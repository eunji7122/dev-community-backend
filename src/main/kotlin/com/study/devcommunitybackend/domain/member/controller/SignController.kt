package com.study.devcommunitybackend.domain.member.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.common.data.dto.TokenDto
import com.study.devcommunitybackend.domain.member.data.dto.LoginMemberRequestDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberRequestDto
import com.study.devcommunitybackend.domain.member.service.SignService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SignController (private val signService: SignService) {

    /**
     * 회원가입
     */
    @PostMapping("/signUp")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberRequestDto): BaseResponseDto<Unit> {
        val resultMsg: String = signService.signUp(memberDtoRequest)
        return BaseResponseDto(message = resultMsg)
    }

    /**
     * 로그인
     */
    @PostMapping("/signIn")
    fun signIn(@RequestBody @Valid loginDto: LoginMemberRequestDto): BaseResponseDto<TokenDto> {
        val tokenInfo = signService.signIn(loginDto)
        return BaseResponseDto(data = tokenInfo)
    }
}