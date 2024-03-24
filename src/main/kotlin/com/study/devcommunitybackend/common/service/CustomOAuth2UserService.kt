package com.study.devcommunitybackend.common.service

import com.study.devcommunitybackend.common.data.dto.OAuthAttributes
import com.study.devcommunitybackend.domain.member.data.entity.Gender
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.member.data.entity.MemberRole
import com.study.devcommunitybackend.domain.member.data.entity.Role
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.member.repository.MemberRoleRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CustomOAuth2UserService (
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        if (userRequest == null) throw OAuth2AuthenticationException("oauth2 error")

        val delegate = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)

        // Social 플랫폼 구분 (ex: google, naver)
        val registrationId = userRequest.clientRegistration.registrationId
        // OAuth2 로그인 진행 시 id가 되는 필드값
        val userNameAttributeName = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        val password = bCryptPasswordEncoder.encode("password" + UUID.randomUUID().toString().substring(0, 6))
        val attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.attributes)

        // 회원 정보가 없으면 회원가입
        val member: Member? = memberRepository.findByLoginId(attributes!!.email)
        var userId = member?.id
        if (member == null) {
            val memberEntity = Member(
                id = null,
                loginId = attributes.email,
                password = password,
                name = attributes.name,
                email = attributes.email,
                birthDate = LocalDate.now(),
                gender = Gender.MAN,
                provider = registrationId,
                providerId = oAuth2User.attributes["sub"].toString()
            )
            userId = memberEntity.id
            memberRepository.save(memberEntity)
            memberRoleRepository.save(MemberRole(Role.MEMBER, memberEntity))
        }

//        return oAuth2User
        return DefaultOAuth2User(Collections.singleton(SimpleGrantedAuthority("ROLE_MEMBER")), attributes.convertToMap(userId!!), "email")
    }
}