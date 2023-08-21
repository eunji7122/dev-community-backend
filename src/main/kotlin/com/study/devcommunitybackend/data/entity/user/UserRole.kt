package com.study.devcommunitybackend.data.entity.user

import jakarta.persistence.*

@Entity
@Table
class UserRole (
    userId: Long,
    roleType: RoleType
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val userId: Long = userId

    @Enumerated(EnumType.STRING)
    val roleType: RoleType = roleType

}