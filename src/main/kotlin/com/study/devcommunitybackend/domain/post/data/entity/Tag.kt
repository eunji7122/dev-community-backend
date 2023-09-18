package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class Tag (
    @Column(nullable = false) val name: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

}