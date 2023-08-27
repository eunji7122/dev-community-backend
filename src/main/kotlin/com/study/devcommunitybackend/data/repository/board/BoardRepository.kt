package com.study.devcommunitybackend.data.repository.board

import com.study.devcommunitybackend.data.entity.borad.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long>{

    fun findByName(name: String): Board?
}