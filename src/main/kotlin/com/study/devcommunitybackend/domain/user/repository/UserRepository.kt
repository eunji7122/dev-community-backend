package com.study.devcommunitybackend.domain.user.repository

import com.study.devcommunitybackend.domain.user.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
}