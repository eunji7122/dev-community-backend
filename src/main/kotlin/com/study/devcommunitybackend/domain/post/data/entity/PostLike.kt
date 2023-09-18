package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.domain.member.data.entity.Member
import jakarta.persistence.*

@Entity
@Table
class PostLike (
    @ManyToOne @JoinColumn(name = "post_id", nullable = false) val post: Post,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) val member: Member
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}