package com.study.devcommunitybackend.domain.member.service

import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import com.study.devcommunitybackend.common.data.dto.TokenDto
import com.study.devcommunitybackend.common.exception.InvalidInputException
import com.study.devcommunitybackend.domain.member.data.dto.LoginMemberRequestDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberRequestDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.member.data.entity.MemberRole
import com.study.devcommunitybackend.domain.member.data.entity.Role
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.member.repository.MemberRoleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService (
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
    fun login(loginDto: LoginMemberRequestDto): TokenDto {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, bCryptPasswordEncoder.encode(loginDto.password))
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return TokenDto(jwtTokenProvider.createToken(authentication), jwtTokenProvider.createRefreshToken(authentication))
    }

    /**
     * 내 정보 보기
     */
    fun searchMyInfo(id: Long): MemberResponseDto {
        val member = memberRepository.findByIdOrNull(id) ?: throw InvalidInputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")
        return member.toDto()
    }

    /**
     * 내 정보 수정
     */
    fun saveMyInfo(memberDtoRequest: MemberRequestDto): String {
        val member = memberDtoRequest.toEntity(bCryptPasswordEncoder)
        memberRepository.save(member)
        return "수정 완료되었습니다."
    }

//    fun createUser(createMemberRequestDto: CreateMemberRequestDto): MemberRequestDto {
//        if (memberRepository.findByEmail(createMemberRequestDto.email) != null) {
//            throw RuntimeException()
//        }
//
//        val newMember = Member(createMemberRequestDto.email,
//            bCryptPasswordEncoder.encode(createMemberRequestDto.password),
//            createMemberRequestDto.name,
//            Role.MEMBER,
//            1000)
//        val savedUser = memberRepository.save(newMember)
//        return MemberRequestDto.fromModel(savedUser)
//    }

//    fun loginUser(loginMemberRequestDto: LoginMemberRequestDto): AuthTokenResponseDto {
//        val user = memberRepository.findByEmail(loginMemberRequestDto.email) ?: throw RuntimeException()
//
//        if (!bCryptPasswordEncoder.matches(loginMemberRequestDto.password, user.password))
//            throw RuntimeException()
//
//        val token = jwtTokenProvider.createToken(user.loginId, user.role.toString())
//        return AuthTokenResponseDto.fromModel(token.accessToken, token.refreshToken)
//    }

}