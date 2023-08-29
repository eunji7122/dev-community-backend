package com.study.devcommunitybackend.domain.comment.data.entity

import com.study.devcommunitybackend.domain.post.data.entity.Post
import com.study.devcommunitybackend.domain.user.data.entity.User
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE comment SET deletedAt = NOW() WHERE id = ?")
class Comment (
    @Column(nullable = false, columnDefinition = "TEXT") val content: String,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) val user: User,
    @ManyToOne @JoinColumn(name = "post_id", nullable = false) val post: Post,
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null

}