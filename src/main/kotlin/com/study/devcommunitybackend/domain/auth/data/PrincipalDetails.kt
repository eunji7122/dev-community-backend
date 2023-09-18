package com.study.devcommunitybackend.domain.auth.data

import com.study.devcommunitybackend.domain.member.data.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class PrincipalDetails (
    val member: Member
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return member.password
    }

    override fun getUsername(): String {
        // 사용자 아이디 반환
        return member.loginId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}