package com.study.devcommunitybackend.data.entity.post

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class Tag (
    name: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val name: String = name

}