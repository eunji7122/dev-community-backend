package com.study.devcommunitybackend.domain.member.service

import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import com.study.devcommunitybackend.common.data.dto.TokenDto
import com.study.devcommunitybackend.common.exception.InvalidInputException
import com.study.devcommunitybackend.domain.member.data.dto.LoginMemberRequestDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberRequestDto
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.member.data.entity.MemberRole
import com.study.devcommunitybackend.domain.member.data.entity.Role
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.member.repository.MemberRoleRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignService (
        private val memberRepository: MemberRepository,
        private val memberRoleRepository: MemberRoleRepository,
        private val jwtTokenProvider: JwtTokenProvider,
        private val authenticationManagerBuilder: AuthenticationManagerBuilder,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    /**
     * 회원가입
     */
    fun signUp(memberRequestDto: MemberRequestDto): String {
        var member: Member? = memberRepository.findByLoginId(memberRequestDto.loginId)
        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        // 사용자 정보 저장
        member = memberRequestDto.toEntity(bCryptPasswordEncoder)
        memberRepository.save(member)

        // 사용자 권한 저장
        val memberRole = MemberRole(Role.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "회원가입이 완료되었습니다."
    }

    /**
     * 로그인
     */
    fun signIn(loginDto: LoginMemberRequestDto): TokenDto {
        val member: Member? = memberRepository.findByLoginId(loginDto.loginId)
        if (!bCryptPasswordEncoder.matches(loginDto.password, member!!.password)) {
            throw RuntimeException()
        }
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, member.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return TokenDto(jwtTokenProvider.createToken(authentication), jwtTokenProvider.createRefreshToken(authentication))
    }

}