package com.study.devcommunitybackend.data.entity.log

import com.study.devcommunitybackend.data.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class PointLog (
    userId: Long,
    amount: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val userId: Long = userId

    @Column(nullable = false)
    val amount: Int = amount

    @Column(nullable = true)
    val postId: Long? = null

    @Column(nullable = true)
    val commentId: Long? = null

}