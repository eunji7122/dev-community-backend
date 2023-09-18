package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.board.data.entity.Board
import com.study.devcommunitybackend.domain.member.data.entity.Member
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE post SET deletedAt = NOW() WHERE id = ?")
class Post (
    @Column(nullable = false) val title: String,
    @Column(nullable = false) val content: String,
    @ManyToOne @JoinColumn(name = "board_id", nullable = false) val board: Board,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) val member: Member,
    @Column(nullable = false) val viewCount: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null
}