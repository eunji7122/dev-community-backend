package com.study.devcommunitybackend.domain.board.data.entity

import com.study.devcommunitybackend.domain.base.entity.BaseEntity
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