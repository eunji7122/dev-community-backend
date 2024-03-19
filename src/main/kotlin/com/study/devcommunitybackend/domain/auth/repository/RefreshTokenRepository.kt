package com.study.devcommunitybackend.domain.auth.repository

import com.study.devcommunitybackend.domain.auth.data.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {

    fun findByUsername(username: String): RefreshToken?

    fun findByToken(token: String): RefreshToken?
}