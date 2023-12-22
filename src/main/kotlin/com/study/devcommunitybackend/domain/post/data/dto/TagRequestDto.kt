package com.study.devcommunitybackend.domain.post.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.post.data.entity.Tag
import jakarta.validation.constraints.NotBlank

data class TagRequestDto(
        val id: Long?,

        @field:NotBlank
        @JsonProperty("name")
        private val _name: String?,
) {

    val name: String
        get() = _name!!

    fun toEntity(): Tag = Tag(id, name)
}
