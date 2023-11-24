package com.study.devcommunitybackend.domain.member.service

import com.study.devcommunitybackend.common.exception.InvalidInputException
import com.study.devcommunitybackend.domain.member.data.dto.MemberRequestDto
import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberRepository: MemberRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

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