package com.study.devcommunitybackend.domain.board.repository

import com.study.devcommunitybackend.domain.board.data.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long>{

    fun findByName(name: String): Board?
}