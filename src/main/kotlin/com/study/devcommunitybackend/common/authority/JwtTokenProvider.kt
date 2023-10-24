package com.study.devcommunitybackend.common.authority

import com.study.devcommunitybackend.common.data.dto.CustomUser
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

const val EXPIRATION_MILLISECONDS: Long = 1000 * 60 * 60 * 12

@Component
class JwtTokenProvider (
//    private val customUserDetailsService: CustomUserDetailsService
) {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    val accessTokenValidMillisecond: Long = 1000L * 60 * 60
    val refreshTokenValidMillisecond: Long = 14 * 24 * 60 * 60 * 1000L

    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    /**
     * token 생성
     */
    fun createToken(authentication: Authentication): String {
        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)
        val now = Date()
        val accessExpiration = Date(now.time + EXPIRATION_MILLISECONDS)

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authorities) // 권한 정보 저장
            .claim("userId", (authentication.principal as CustomUser).userId) // 유저 id 저장
            .setIssuedAt(now) // 발행 시간
            .setExpiration(accessExpiration) // 만료 시간
            .signWith(key, SignatureAlgorithm.HS256) // 암호화 알고리즘
            .compact()
//        return TokenDto("Bearer", accessToken)
    }

    /**
     * refresh token 생성
     */
    fun createRefreshToken(authentication: Authentication): String {
        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)
        val now = Date()
        val accessExpiration = Date(now.time + refreshTokenValidMillisecond)

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authorities) // 권한 정보 저장
            .claim("userId", (authentication.principal as CustomUser).userId) // 유저 id 저장
            .setIssuedAt(now) // 발행 시간
            .setExpiration(accessExpiration) // 만료 시간
            .signWith(key, SignatureAlgorithm.HS256) // 암호화 알고리즘
            .compact()
    }


    /**
     * token 정보 추출
     */
    fun getAuthentication(token: String): Authentication {
        val claims: Claims = getClaims(token)
        val auth = claims["auth"] ?: throw RuntimeException("잘못된 토큰 입니다.")
        val userId = claims["userId"] ?: throw RuntimeException("잘못된 토큰 입니다.")

        // 권한 정보 추출
        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails =
            CustomUser(userId.toString().toLong(), claims.subject, "", authorities)
//        val principal: UserDetails = customUserDetailsService.loadUserByUsername(token)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    /**
     * Token 검증
     */
    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {} // Invalid JWT Token
                is MalformedJwtException -> {} // Invalid JWT Token
                is ExpiredJwtException -> {} // Expired JWT Token
                is UnsupportedJwtException -> {} // Unsupported JWT Token
                is IllegalArgumentException -> {} // JWT claims string is empty
                else -> {} // else
            }
            println(e.message)
        }
        return false
    }

    private fun getClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

//    fun createToken(userEmail: String, role: String): AuthTokenRequestDto {
//        val claims = Jwts.claims().setSubject(userEmail)
//        claims["role"] = role
//        val now = Date()
//
//        val accessToken = Jwts.builder()
//            .setClaims(claims)
//            .setIssuedAt(now)
//            .setExpiration(Date(now.time + accessTokenValidMillisecond))
//            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
//            .compact()
//
//        val refreshToken = Jwts.builder()
//            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//            .setExpiration(Date(now.time + refreshTokenValidMillisecond))
//            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
//            .compact()
//
//        return AuthTokenRequestDto.fromModel(grantType = "Bearer", accessToken, refreshToken, accessTokenValidMillisecond);
//
//    }
//
//    fun getAuthentication(token: String): Authentication? {
//        val userDetails = userDetailsService.loadUserByUsername(this.getUsername(token)) ?: return null
//        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
//    }
//
//    fun getUsername(token: String): String {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).body.subject
//    }
//
//    fun resolveToken(request: HttpServletRequest): String {
//        return request.getHeader("Authorization")
//    }
//
//    fun validateToken(token: String): Boolean {
//        try {
//            val claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
//            return !claimsJws.body.expiration.before(Date())
//        } catch (e: Exception) {
//            println(e)
//            return false
//        }
//    }

}