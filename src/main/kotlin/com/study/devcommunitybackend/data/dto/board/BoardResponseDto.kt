package com.study.devcommunitybackend.data.dto.board

import com.study.devcommunitybackend.data.entity.borad.Board

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
