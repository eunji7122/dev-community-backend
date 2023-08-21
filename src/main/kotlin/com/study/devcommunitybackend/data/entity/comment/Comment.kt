package com.study.devcommunitybackend.data.entity.comment

import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.w3c.dom.Text
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE comment SET deletedAt = NOW() WHERE id = ?")
class Comment (
    content: String,
    userId: Long,
    postId: Long,
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String = content

    @Column(nullable = false)
    val userId: Long = userId

    @Column(nullable = false)
    val postId: Long = postId

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null

}