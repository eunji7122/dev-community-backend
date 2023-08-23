package com.study.devcommunitybackend.data.entity.post

import com.study.devcommunitybackend.data.entity.base.BaseEntity
import com.study.devcommunitybackend.data.entity.borad.Board
import com.study.devcommunitybackend.data.entity.user.User
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE post SET deletedAt = NOW() WHERE id = ?")
class Post (
    title: String,
    content: String,
    board: Board,
    user: User,
    viewCount: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    val title: String = title

    @Column(nullable = false)
    val content: String = content

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    val board: Board = board

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User = user

    @Column(nullable = false)
    val viewCount: Int = viewCount

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null
}