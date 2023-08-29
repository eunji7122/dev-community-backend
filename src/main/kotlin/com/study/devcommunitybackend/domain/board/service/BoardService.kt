package com.study.devcommunitybackend.domain.board.service

import com.study.devcommunitybackend.domain.board.data.dto.BoardResponseDto
import com.study.devcommunitybackend.domain.board.data.entity.Board
import com.study.devcommunitybackend.domain.board.repository.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class BoardService (
    private val boardRepository: BoardRepository
) {

    fun createBoard(boardResponseDto: BoardResponseDto) : BoardResponseDto {
        if (boardRepository.findByName(boardResponseDto.name) != null) {
            throw RuntimeException()
        }

        val newBoard = Board(boardResponseDto.name, boardResponseDto.usingStatus)
        val savedBoard = boardRepository.save(newBoard)
        return BoardResponseDto.fromModel(savedBoard)
    }

    fun getBoard(name: String) : BoardResponseDto? {
        val foundBoard = boardRepository.findByName(name) ?: return null
        return BoardResponseDto.fromModel(foundBoard)
    }

    fun getAllBoards() : List<BoardResponseDto>? {
        return boardRepository.findAll().stream().map { BoardResponseDto.fromModel(it) }.toList()
    }

    fun updateBoard(name: String, boardResponseDto: BoardResponseDto) : BoardResponseDto {
        val foundBoard = boardRepository.findByName(name)
        if (foundBoard != null) {
            boardRepository.delete(foundBoard)
        }

        val newBoard = Board(boardResponseDto.name, boardResponseDto.usingStatus)
        val savedBoard = boardRepository.save(newBoard)
        return BoardResponseDto.fromModel(savedBoard)
    }

    fun deleteBoard(name: String) {
        val foundBoard = boardRepository.findByName(name)
        if (foundBoard != null) {
            boardRepository.delete(foundBoard)
        }
    }
}