package com.study.devcommunitybackend.domain.board.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.board.data.dto.BoardResponseDto
import jakarta.persistence.*

@Entity
@Table
class Board (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false)
        var usingStatus: Boolean
) : BaseEntity() {

    fun toDto(): BoardResponseDto = BoardResponseDto(id!!, name, usingStatus)
}