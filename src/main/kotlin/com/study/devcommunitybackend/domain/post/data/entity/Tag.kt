package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.post.data.dto.TagResponseDto
import jakarta.persistence.*

@Entity
@Table
class Tag (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var name: String
) : BaseEntity() {

        fun toDto(): TagResponseDto = TagResponseDto(id!!, name)

}