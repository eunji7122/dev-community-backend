package com.study.devcommunitybackend.domain.board.service

import com.study.devcommunitybackend.domain.board.data.entity.Board
import com.study.devcommunitybackend.domain.board.repository.BoardRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BoardServiceTest @Autowired constructor(
    val boardRepository: BoardRepository
){
    @Test
    fun createBoard() {

        //given TODO("테스트 대상을 만들더 준비하는 과정")
        val newBoard = Board(null,"test", true)

        //when TODO("실제 우리가 테스트하고 싶은 기능을 호출하는 과정")
        boardRepository.save(newBoard)
        val findBoard = boardRepository.findByName("test")

        //then TODO("호출 이후 의도한대로 결과가 나왔는지 확인하는 과정")
        if (findBoard != null) {
            assertThat(newBoard.name).isEqualTo(findBoard.name)
        }

    }

    @Test
    fun getBoard() {
    }

    @Test
    fun getAllBoards() {
    }

    @Test
    fun updateBoard() {
    }

    @Test
    fun deleteBoard() {
    }
}