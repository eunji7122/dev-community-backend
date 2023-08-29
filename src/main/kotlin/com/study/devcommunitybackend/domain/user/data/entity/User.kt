package com.study.devcommunitybackend.domain.user.data.entity

import com.study.devcommunitybackend.domain.base.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class User(
    @Column(nullable = false, unique = true) val email: String,
    @Column(nullable = false, unique = true) val password: String,
    @Column(nullable = false) val name: String,
    @Column(nullable = false) val point: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}