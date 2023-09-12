package com.study.devcommunitybackend.domain.auth.service

import com.study.devcommunitybackend.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl (
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        return userRepository.findByEmail(username.toString())
    }
}