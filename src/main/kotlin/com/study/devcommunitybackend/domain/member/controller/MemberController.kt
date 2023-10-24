package com.study.devcommunitybackend.domain.member.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.common.data.dto.CustomUser
import com.study.devcommunitybackend.common.data.dto.TokenDto
import com.study.devcommunitybackend.domain.member.data.dto.LoginMemberRequestDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberRequestDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto
import com.study.devcommunitybackend.domain.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController (
    private val memberService: MemberService
) {

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberRequestDto): BaseResponseDto<Unit> {
        val resultMsg: String = memberService.signUp(memberDtoRequest)
        return BaseResponseDto(message = resultMsg)
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginMemberRequestDto): BaseResponseDto<TokenDto> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponseDto(data = tokenInfo)
    }

    /**
     * 내 정보 보기
     */
    @GetMapping("/info")
    fun searchMyInfo(): BaseResponseDto<MemberResponseDto> {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .userId
        val response = memberService.searchMyInfo(userId)
        return BaseResponseDto(data = response)
    }

    /**
     * 내 정보 저장
     */
    @PutMapping("/info")
    fun saveMyInfo(@RequestBody @Valid memberDtoRequest: MemberRequestDto): BaseResponseDto<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId

        memberDtoRequest.id = userId
        val resultMsg: String = memberService.saveMyInfo(memberDtoRequest)
        return BaseResponseDto(message = resultMsg)
    }

//    @PostMapping("/create")
//    fun createUser(@RequestBody createMemberRequestDto: CreateMemberRequestDto): ResponseEntity<MemberRequestDto> {
//        val userResponseDto = memberService.createUser(createMemberRequestDto)
//        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto)
//    }
//
//    @PostMapping("/login")
//    fun loginUser(@RequestBody loginMemberRequestDto: LoginMemberRequestDto): AuthTokenResponseDto {
//        return memberService.loginUser(loginMemberRequestDto)
//    }

}