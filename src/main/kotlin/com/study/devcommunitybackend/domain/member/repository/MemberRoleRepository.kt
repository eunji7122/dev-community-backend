package com.study.devcommunitybackend.domain.member.repository

import com.study.devcommunitybackend.domain.member.data.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRoleRepository : JpaRepository<MemberRole, Long> {
}