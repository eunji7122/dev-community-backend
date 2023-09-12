package com.study.devcommunitybackend.domain.user.data.entity

import com.study.devcommunitybackend.global.common.entity.BaseEntity
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table
class User(
    @Column(nullable = false, unique = true) var email: String,
    @Column(nullable = false) private var password: String,
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var role: RoleType,
    @Column(nullable = false) var point: Int
) : BaseEntity(), UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
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