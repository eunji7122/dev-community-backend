package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.post.data.dto.PostTagResponseDto
import jakarta.persistence.*

@Entity
@Table
class PostTag (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "post_id", nullable = false)
        val post: Post,

        @ManyToOne
        @JoinColumn(name = "tag_id", nullable = false)
        val tag: Tag
) : BaseEntity() {

    fun toDto(): PostTagResponseDto = PostTagResponseDto(id!!, post.toDto(), tag.toDto())

}