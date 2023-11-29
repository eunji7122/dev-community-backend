package com.study.devcommunitybackend.domain.post.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.board.data.entity.Board
import com.study.devcommunitybackend.domain.member.data.entity.Member
import com.study.devcommunitybackend.domain.post.data.dto.PostResponseDto
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE post SET deleted_at = NOW() WHERE id = ?")
class Post (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var title: String,

        @Column(nullable = false)
        var content: String,

        @ManyToOne @JoinColumn(name = "board_id", nullable = false)
        var board: Board,

        @ManyToOne @JoinColumn(name = "user_id", nullable = false)
        var member: Member,

        @Column(nullable = false)
        var viewCount: Int
) : BaseEntity() {

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null

    fun toDto(): PostResponseDto = PostResponseDto(id!!, title, content, board.toDto(), member.toDto())
}