package com.study.devcommunitybackend.domain.board.controller

import com.study.devcommunitybackend.domain.board.data.dto.BoardResponseDto
import com.study.devcommunitybackend.domain.board.service.BoardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/boards")
class BoardController (
    private val boardService: BoardService
){
    @GetMapping()
    fun getAllBoards(): ResponseEntity<List<BoardResponseDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getAllBoards())
    }

    @GetMapping("/{name}")
    fun getBoard(@PathVariable name: String): ResponseEntity<BoardResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(name))
    }

    @PostMapping()
    fun createBoard(@RequestBody boardResponseDto: BoardResponseDto): ResponseEntity<BoardResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.createBoard(boardResponseDto))
    }

    @DeleteMapping("/{name}/delete")
    fun deleteBoard(@PathVariable name: String): ResponseEntity<String> {
        boardService.deleteBoard(name)
        return ResponseEntity.status(HttpStatus.OK).body("Success to delete")
    }

    @PutMapping("/{name}/update")
    fun updateBoard(@PathVariable name: String, @RequestBody boardResponseDto: BoardResponseDto): ResponseEntity<BoardResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(name, boardResponseDto))
    }
}