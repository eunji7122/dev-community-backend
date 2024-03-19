package com.study.devcommunitybackend.common.authority

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token = resolveToken(request as HttpServletRequest)

        // 토큰이 null이고 요청이 회원가입/로그인/토큰재발급인 경우 처리?
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                val authentication = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            request.setAttribute("exception", e)
        }

        chain?.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) &&
            bearerToken.startsWith("Bearer")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
}

//class JwtAuthenticationFilter (
//    val jwtTokenProvider: JwtTokenProvider
//): OncePerRequestFilter() {
//
//    val TOKEN_PREFIX: String = "Bearer"
//
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        val token = jwtTokenProvider.resolveToken(request)
//        if (token != null && jwtTokenProvider.validateToken(parseToken(token))) {
//
//            val authentication = jwtTokenProvider.getAuthentication(parseToken(token))
//            SecurityContextHolder.getContext().authentication = authentication
//        }
//    }
//
//    private fun parseToken(token: String): String {
//        if (token.startsWith(TOKEN_PREFIX)) {
//            return token.substring(TOKEN_PREFIX.length).trim()
//        }
//        return token
//    }
//}