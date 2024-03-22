package com.study.devcommunitybackend.common.config

import com.study.devcommunitybackend.common.authority.CustomAccessDeniedHandler
import com.study.devcommunitybackend.common.authority.CustomAuthenticationEntryPoint
import com.study.devcommunitybackend.common.authority.JwtAuthenticationFilter
import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import com.study.devcommunitybackend.common.handler.OAuth2AuthenticationFailureHandler
import com.study.devcommunitybackend.common.handler.OAuth2AuthenticationSuccessHandler
import com.study.devcommunitybackend.common.service.CustomOAuth2UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
class SecurityConfig (
    private val jwtTokenProvider: JwtTokenProvider,
//    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
    private val oAuth2AuthenticationFailureHandler: OAuth2AuthenticationFailureHandler
) {

    @Value("\${spring.security.oauth2.client.registration.goggle.client-id}")
    lateinit var googleClientId: String
    @Value("\${spring.security.oauth2.client.registration.goggle.client-secret}")
    lateinit var googleClientSecretKey: String

    @Order(0)
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity, introspector: HandlerMappingIntrospector): SecurityFilterChain? {
//        http
//            .httpBasic { it.disable() }
//            .csrf { it.disable() }
//            .sessionManagement {
//                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            }
////            .authorizeHttpRequests {
////                it.requestMatchers("/member/signup", "/member/login").permitAll()
////                .requestMatchers("/member/info/**").hasRole("MEMBER")
////                .anyRequest().authenticated()
////            }
//            .addFilterBefore(
//                JwtAuthenticationFilter(jwtTokenProvider),
//                UsernamePasswordAuthenticationFilter::class.java
//            )
//        return http.build()
        val mvcMatcherBuilder = MvcRequestMatcher.Builder(introspector)
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests{ requests -> requests
                .requestMatchers(
                        mvcMatcherBuilder.pattern("/*"),
                        mvcMatcherBuilder.pattern("/login"),
                        mvcMatcherBuilder.pattern("/signUp"),
                        mvcMatcherBuilder.pattern("/signIn"),
                    mvcMatcherBuilder.pattern("/auth/reissue"),
                ).permitAll()
                    .requestMatchers(mvcMatcherBuilder.pattern("/member/info")).hasAnyRole("MEMBER", "ADMIN")
                    .requestMatchers(mvcMatcherBuilder.pattern("/posts")).hasAnyRole("MEMBER", "ADMIN")
                    .requestMatchers(mvcMatcherBuilder.pattern("/boards")).hasRole("ADMIN")
                    .requestMatchers(mvcMatcherBuilder.pattern("/comments")).hasAnyRole("MEMBER", "ADMIN")
//                    .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/profile/*")).permitAll()
                .anyRequest().authenticated()
            }
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
//            .exceptionHandling { it.authenticationEntryPoint(customAuthenticationEntryPoint) }
            .exceptionHandling { it.accessDeniedHandler(customAccessDeniedHandler) }
            .oauth2Login {
                it.userInfoEndpoint { info -> info.userService(customOAuth2UserService) }
                it.successHandler(oAuth2AuthenticationSuccessHandler)
                it.failureHandler(oAuth2AuthenticationFailureHandler)
            }
           // .logout { it.logoutSuccessUrl("/") }

        return http.build()
    }

//    @Bean
//    fun webSecurityCustomizer(): WebSecurityCustomizer? {
//        return WebSecurityCustomizer { web: WebSecurity ->
//            web.ignoring()
//                .requestMatchers(*AUTH_WHITELIST)
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//        }
//    }
//
//    @Bean
//    @Throws(Exception::class)
//    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
//        return authenticationConfiguration.authenticationManager
//    }

        @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository? {
        return InMemoryClientRegistrationRepository(googleClientRegistration())
    }

    @Bean
    fun authorizedClientService(
        clientRegistrationRepository: ClientRegistrationRepository?
    ): OAuth2AuthorizedClientService? {
        return InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository)
    }

    @Bean
    fun authorizedClientRepository(
        authorizedClientService: OAuth2AuthorizedClientService?
    ): OAuth2AuthorizedClientRepository? {
        return AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService)
    }

    private fun googleClientRegistration(): ClientRegistration? {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
            .clientId(googleClientId)
            .clientSecret(googleClientSecretKey)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("http://localhost:8070/login/oauth2/code/google")
            .scope("profile", "email")
            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .clientName("Google")
            .build()
    }

}