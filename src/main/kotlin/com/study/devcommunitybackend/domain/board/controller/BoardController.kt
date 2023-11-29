package com.study.devcommunitybackend.domain.board.controller

import com.study.devcommunitybackend.common.data.dto.BaseResponseDto
import com.study.devcommunitybackend.domain.board.data.dto.BoardRequestDto
import com.study.devcommunitybackend.domain.board.data.dto.BoardResponseDto
import com.study.devcommunitybackend.domain.board.service.BoardService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/boards")
class BoardController (
    private val boardService: BoardService
){
    @GetMapping()
    fun getAllBoards(): BaseResponseDto<List<BoardResponseDto>> {
        val boards = boardService.getAllBoards()
        return BaseResponseDto(data = boards)
    }

    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Long): BaseResponseDto<BoardResponseDto> {
        val board = boardService.getBoard(id)
        return BaseResponseDto(data = board)
    }

    @PostMapping()
    fun createBoard(@RequestBody boardRequestDto: BoardRequestDto): BaseResponseDto<BoardResponseDto> {
        val createdBoard = boardService.createBoard(boardRequestDto)
        return BaseResponseDto(data = createdBoard)
    }

    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: Long) {
        boardService.deleteBoard(id)
    }

    @PutMapping()
    fun updateBoard(@RequestBody boardResponseDto: BoardResponseDto): BaseResponseDto<BoardResponseDto> {
        val updatedBoard = boardService.updateBoard(boardResponseDto)
        return BaseResponseDto(data = updatedBoard)
    }
}