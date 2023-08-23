package com.study.devcommunitybackend.data.entity.post

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class PostTag (
    postId: Long,
    tagId: Long
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val postId: Long = postId

    @Column(nullable = false)
    val tagId: Long = tagId

}