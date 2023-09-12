package com.study.devcommunitybackend.global.config.security

import com.study.devcommunitybackend.domain.auth.data.dto.AuthTokenRequestDto
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class JwtTokenProvider (
    private val userDetailsService: UserDetailsService
){
    var secretKey: String = "testSecretKey20230327testSecretKey20230327testSecretKey20230327"
    val accessTokenValidMillisecond: Long = 1000L * 60 * 60
    val refreshTokenValidMillisecond: Long = 14 * 24 * 60 * 60 * 1000L

    @PostConstruct
    protected fun init()  {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray(StandardCharsets.UTF_8));
    }

    fun createToken(userEmail: String, role: String): AuthTokenRequestDto {
        val claims = Jwts.claims().setSubject(userEmail)
        claims["role"] = role
        val now = Date()

        val accessToken = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + accessTokenValidMillisecond))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
            .compact()

        val refreshToken = Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setExpiration(Date(now.time + refreshTokenValidMillisecond))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
            .compact()

        return AuthTokenRequestDto.fromModel(grantType = "Bearer", accessToken, refreshToken, accessTokenValidMillisecond);

    }

    fun getAuthentication(token: String): Authentication? {
        val userDetails = userDetailsService.loadUserByUsername(this.getUsername(token)) ?: return null
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String {
        return request.getHeader("Authorization")
    }

    fun validateToken(token: String): Boolean {
        try {
            val claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            return !claimsJws.body.expiration.before(Date())
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

}