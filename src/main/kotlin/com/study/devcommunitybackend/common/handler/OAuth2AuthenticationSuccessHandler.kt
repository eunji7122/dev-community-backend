package com.study.devcommunitybackend.common.handler

import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.nio.charset.StandardCharsets

@Component
class OAuth2AuthenticationSuccessHandler(
    private val jwtTokenProvider: JwtTokenProvider
): SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val oAuth2User = authentication?.principal as OAuth2User

        val accessToken = jwtTokenProvider.createAccessToken(authentication)
        val refreshToken = jwtTokenProvider.createRefreshToken(authentication)

        val targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8070/")
            .queryParam("accessToken", accessToken)
            .queryParam("refreshToken", refreshToken)
            .build()
            .encode(StandardCharsets.UTF_8)
            .toUriString()

        println("소셜 로그인 성공")
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }
}