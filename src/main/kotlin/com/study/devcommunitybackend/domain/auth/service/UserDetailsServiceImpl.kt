package com.study.devcommunitybackend.domain.auth.service

import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl (
    private val userRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        return null
//        return userRepository.findByEmail(username.toString())
    }
}