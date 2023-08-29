package com.study.devcommunitybackend.domain.user.data.entity

import jakarta.persistence.*

@Entity
@Table
class UserRole (
    @Column(nullable = false) val userId: Long,
    @Enumerated(EnumType.STRING) val roleType: RoleType
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}