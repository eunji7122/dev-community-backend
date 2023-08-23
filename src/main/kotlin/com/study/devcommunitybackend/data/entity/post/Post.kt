package com.study.devcommunitybackend.data.entity.post

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE post SET deletedAt = NOW() WHERE id = ?")
class Post (
    title: String,
    content: String,
    boardId: Long,
    userId: Long,
    viewCount: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val title: String = title

    @Column(nullable = false)
    val content: String = content

    @Column(nullable = false)
    val boardId: Long = boardId

    @Column(nullable = false)
    val userId: Long = userId

    @Column(nullable = false)
    val viewCount: Int = viewCount

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null
}