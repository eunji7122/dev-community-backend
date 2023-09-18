package com.study.devcommunitybackend.domain.member.repository

import com.study.devcommunitybackend.domain.member.data.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByLoginId(loginId: String): Member?
}