package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.domain.base.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class PostTag (
    @ManyToOne @JoinColumn(name = "post_id", nullable = false) val post: Post,
    @ManyToOne @JoinColumn(name = "tag_id", nullable = false) val tag: Tag
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}