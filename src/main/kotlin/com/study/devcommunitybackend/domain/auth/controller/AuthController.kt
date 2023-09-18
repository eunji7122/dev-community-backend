package com.study.devcommunitybackend.domain.auth.controller

import com.study.devcommunitybackend.domain.auth.service.AuthService
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController (
    val authService: AuthService
) {
}