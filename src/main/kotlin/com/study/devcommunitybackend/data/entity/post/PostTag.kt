package com.study.devcommunitybackend.data.entity.post

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class PostTag (
    post: Post,
    tag: Tag
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post = post

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    val tag: Tag = tag

}