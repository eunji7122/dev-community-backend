package com.study.devcommunitybackend.domain.point.service

import com.study.devcommunitybackend.domain.comment.repository.CommentRepository
import com.study.devcommunitybackend.domain.member.repository.MemberRepository
import com.study.devcommunitybackend.domain.point.data.dto.PointLogRequestDto
import com.study.devcommunitybackend.domain.point.data.dto.PointLogResponseDto
import com.study.devcommunitybackend.domain.point.repository.PointLogRepository
import com.study.devcommunitybackend.domain.point.repository.PointRepository
import com.study.devcommunitybackend.domain.post.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class PointLogService (
        private val pointLogRepository: PointLogRepository,
        private val pointRepository: PointRepository,
        private val memberRepository: MemberRepository,
        private val postRepository: PostRepository,
        private val commentRepository: CommentRepository
) {

    fun createPointLog(pointLogRequestDto: PointLogRequestDto) : PointLogResponseDto {
        val foundMember = memberRepository.findByIdOrNull(pointLogRequestDto.memberId)
        val foundPost = postRepository.findByIdOrNull(pointLogRequestDto.postId)
        val foundComment = commentRepository.findByIdOrNull(pointLogRequestDto.commentId)

        return pointLogRepository.save(pointLogRequestDto.toEntity(foundMember!!, foundPost!!, foundComment!!)).toDto()
    }

    fun getAllPointLogByMember(memberId: Long) : List<PointLogResponseDto> {
        return pointLogRepository.findAllByMemberId(memberId).stream().map { it.toDto() }.toList()
    }

}