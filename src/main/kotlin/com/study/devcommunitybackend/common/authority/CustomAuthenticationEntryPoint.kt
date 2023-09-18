package com.study.devcommunitybackend.common.authority

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.devcommunitybackend.common.data.dto.EntryPointErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
class CustomAuthenticationEntryPoint: AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val objectMapper = ObjectMapper()

        val entryPointErrorResponse = EntryPointErrorResponse(authException?.message.toString())

        if (response != null) {
            response.status = 401
            response.contentType = "application/json"
            response.characterEncoding = "utf-8"
            response.writer.write(objectMapper.writeValueAsString(entryPointErrorResponse))
        }
    }
}