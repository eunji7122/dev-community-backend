package com.study.devcommunitybackend.global.config.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter (
    val jwtTokenProvider: JwtTokenProvider
): OncePerRequestFilter() {

    val TOKEN_PREFIX: String = "Bearer"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = jwtTokenProvider.resolveToken(request)
        if (token != null && jwtTokenProvider.validateToken(parseToken(token))) {

            val authentication = jwtTokenProvider.getAuthentication(parseToken(token))
            SecurityContextHolder.getContext().authentication = authentication
        }
    }

    private fun parseToken(token: String): String {
        if (token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length).trim()
        }
        return token
    }
}