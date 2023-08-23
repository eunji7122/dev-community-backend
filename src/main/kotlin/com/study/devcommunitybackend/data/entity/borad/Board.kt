package com.study.devcommunitybackend.data.entity.borad

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class Board (
    name: String,
    usingStatus: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val name: String = name

    @Column(nullable = false)
    val usingStatus: Boolean = usingStatus

}