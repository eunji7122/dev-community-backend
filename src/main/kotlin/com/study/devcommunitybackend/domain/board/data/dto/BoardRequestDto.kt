package com.study.devcommunitybackend.domain.board.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.devcommunitybackend.domain.board.data.entity.Board
import jakarta.validation.constraints.NotBlank

data class BoardRequestDto(

        var id: Long?,

        @field:NotBlank
        @JsonProperty("name")
        private val _name: String?,

        @field:NotBlank
        @JsonProperty("usingStatus")
        private val _usingStatus: Boolean?,
) {
    val name: String
        get() = _name!!

    val usingStatus: Boolean
        get() = _usingStatus!!

    fun toEntity(): Board = Board(id, name, usingStatus)
}
