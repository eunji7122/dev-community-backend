package com.study.devcommunitybackend.domain.board.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class CreateBoardRequestDto(

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
}
