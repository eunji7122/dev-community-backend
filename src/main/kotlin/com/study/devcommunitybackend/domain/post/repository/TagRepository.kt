package com.study.devcommunitybackend.domain.post.repository

import com.study.devcommunitybackend.domain.post.data.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, Long> {

    fun findByName(name: String): Tag?
}