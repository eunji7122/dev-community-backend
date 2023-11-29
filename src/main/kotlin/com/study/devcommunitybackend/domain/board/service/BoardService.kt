package com.study.devcommunitybackend.domain.board.service

import com.study.devcommunitybackend.domain.board.data.dto.BoardRequestDto
import com.study.devcommunitybackend.domain.board.data.dto.BoardResponseDto
import com.study.devcommunitybackend.domain.board.repository.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class BoardService(
    private val boardRepository: BoardRepository,
) {

    fun createBoard(boardRequestDto: BoardRequestDto) : BoardResponseDto? {
        return boardRepository.save(boardRequestDto.toEntity()).toDto()
    }

    fun getBoard(id: Long) : BoardResponseDto? {
        val foundBoard = boardRepository.findByIdOrNull(id)
        return foundBoard?.toDto()
    }

    fun getAllBoards() : List<BoardResponseDto>? {
        return boardRepository.findAll().stream().map { BoardResponseDto.fromModel(it) }.toList()
    }

    fun updateBoard(boardResponseDto: BoardResponseDto) : BoardResponseDto? {
        val foundBoard = boardRepository.findByIdOrNull(boardResponseDto.id)

        if (foundBoard != null) {
            foundBoard.name = boardResponseDto.name
            foundBoard.usingStatus = boardResponseDto.usingStatus
            boardRepository.save(foundBoard)
            return foundBoard.toDto()
        }

        return null
    }

    fun deleteBoard(id: Long) {
        val foundBoard = boardRepository.findByIdOrNull(id)
        if (foundBoard != null) {
            boardRepository.delete(foundBoard)
        }
    }
}