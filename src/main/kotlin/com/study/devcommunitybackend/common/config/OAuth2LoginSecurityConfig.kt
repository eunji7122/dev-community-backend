//package com.study.devcommunitybackend.common.config
//
//import com.study.devcommunitybackend.common.handler.OAuth2AuthenticationFailureHandler
//import com.study.devcommunitybackend.common.handler.OAuth2AuthenticationSuccessHandler
//import com.study.devcommunitybackend.common.service.CustomOAuth2UserService
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.annotation.Order
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
//import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
//import org.springframework.security.oauth2.client.registration.ClientRegistration
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
//import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
//import org.springframework.security.oauth2.core.AuthorizationGrantType
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod
//import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector
//
//
//@Configuration
//@EnableWebSecurity
//class OAuth2LoginSecurityConfig (
//    private val customOAuth2UserService: CustomOAuth2UserService,
//    private val oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
//    private val oAuth2AuthenticationFailureHandler: OAuth2AuthenticationFailureHandler
//) {
//
//    @Value("\${spring.security.oauth2.client.registration.goggle.client-id}")
//    lateinit var googleClientId: String
//    @Value("\${spring.security.oauth2.client.registration.goggle.client-secret}")
//    lateinit var googleClientSecretKey: String
//
//    @Order(1)
//    @Bean
//    @Throws(Exception::class)
//    fun securityFilterChain(http: HttpSecurity, introspector: HandlerMappingIntrospector): SecurityFilterChain? {
//        http
//            .oauth2Login {
//                it.userInfoEndpoint { info -> info.userService(customOAuth2UserService) }
//                it.successHandler(oAuth2AuthenticationSuccessHandler)
//                it.failureHandler(oAuth2AuthenticationFailureHandler)
//            }
////            .oauth2Login {
////                it.userInfoEndpoint().userService(customUserDetailService) // #2 로그인 성공 후에 사용할 service 등록
////                it.defaultSuccessUrl("/auth/login") // #3
////                it.failureUrl("/fail")
////            }
//
//        return http.build()
//    }
//
//    @Bean
//    fun clientRegistrationRepository(): ClientRegistrationRepository? {
//        return InMemoryClientRegistrationRepository(googleClientRegistration())
//    }
//
//    @Bean
//    fun authorizedClientService(
//        clientRegistrationRepository: ClientRegistrationRepository?
//    ): OAuth2AuthorizedClientService? {
//        return InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository)
//    }
//
//    @Bean
//    fun authorizedClientRepository(
//        authorizedClientService: OAuth2AuthorizedClientService?
//    ): OAuth2AuthorizedClientRepository? {
//        return AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService)
//    }
//
//    private fun googleClientRegistration(): ClientRegistration? {
//        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
//            .clientId(googleClientId)
//            .clientSecret(googleClientSecretKey)
//            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .redirectUri("http://localhost:8070/login/oauth2/code/google")
//            .scope("profile", "email")
//            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//            .userNameAttributeName(IdTokenClaimNames.SUB)
//            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//            .clientName("Google")
//            .build()
//    }
//}