package com.study.devcommunitybackend.data.entity.borad

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class Board (
    @Column(nullable = false) val name: String,
    @Column(nullable = false) val usingStatus: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}