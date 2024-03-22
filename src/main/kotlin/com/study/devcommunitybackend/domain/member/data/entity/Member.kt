package com.study.devcommunitybackend.domain.member.data.entity

import com.study.devcommunitybackend.common.data.entity.BaseEntity
import com.study.devcommunitybackend.domain.member.data.dto.MemberResponseDto
import jakarta.persistence.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
@Table
class Member(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = 30, updatable = false)
    var loginId: String,

    @Column(nullable = false, length = 100)
    var password: String,

    @Column(nullable = false, length = 10)
    var name: String,

    @Column(nullable = false, length = 30)
    val email: String,

    @Column(nullable = true)
    @Temporal(TemporalType.DATE) // 날짜 형식만 입력 받음
    val birthDate: LocalDate?,

    @Column(nullable = true, length = 5)
    @Enumerated(EnumType.STRING)
    val gender: Gender?,

    // OAuth2의 어떤 Social 플랫폼을 이용하는지
    @Column(nullable = true)
    val provider: String?,

    // OAuth2가 제공하는 ID
    @Column(nullable = true)
    val providerId: String?,

    ) : BaseEntity() {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val memberRole: List<MemberRole>? = null

    private fun LocalDate.formatDate(): String =
        this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

    fun toDto(): MemberResponseDto =
        MemberResponseDto(
            id!!,
            loginId,
            name,
            email,
            birthDate!!.formatDate(),
            gender!!.desc,
        )

}