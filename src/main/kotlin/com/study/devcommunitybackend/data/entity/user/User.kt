package com.study.devcommunitybackend.data.entity.user

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class User(
    email: String,
    password: String,
    name: String,
    point: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false, unique = true)
    val email: String = email

    @Column(nullable = false, unique = true)
    val password: String = password

    @Column(nullable = false)
    val name: String = name

    @Column(nullable = false)
    val point: Int = point

}