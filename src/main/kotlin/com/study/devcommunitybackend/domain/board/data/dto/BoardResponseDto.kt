package com.study.devcommunitybackend.domain.board.data.dto

import com.study.devcommunitybackend.domain.board.data.entity.Board

data class BoardResponseDto(
    val name: String,
    val usingStatus: Boolean
) {
    companion object {
        fun fromModel(board: Board): BoardResponseDto {
            return BoardResponseDto(board.name, board.usingStatus)
        }
    }
}
