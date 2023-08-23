package com.study.devcommunitybackend.data.entity.comment

import com.study.devcommunitybackend.data.entity.post.Post
import com.study.devcommunitybackend.data.entity.user.User
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.w3c.dom.Text
import java.time.LocalDateTime

@Entity
@Table
@SQLDelete(sql = "UPDATE comment SET deletedAt = NOW() WHERE id = ?")
class Comment (
    content: String,
    user: User,
    post: Post,
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String = content

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User = user

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post = post

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null

}