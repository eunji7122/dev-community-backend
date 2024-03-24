package com.study.devcommunitybackend.common.config

import com.study.devcommunitybackend.common.authority.CustomAccessDeniedHandler
import com.study.devcommunitybackend.common.authority.CustomAuthenticationEntryPoint
import com.study.devcommunitybackend.common.authority.JwtAuthenticationFilter
import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import com.study.devcommunitybackend.common.handler.OAuth2AuthenticationFailureHandler
import com.study.devcommunitybackend.common.handler.OAuth2AuthenticationSuccessHandler
import com.study.devcommunitybackend.common.service.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
class SecurityConfig (
    private val jwtTokenProvider: JwtTokenProvider,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
    private val oAuth2AuthenticationFailureHandler: OAuth2AuthenticationFailureHandler
) {


//    @Order(0)
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
                    mvcMatcherBuilder.pattern("/"),
                    mvcMatcherBuilder.pattern("/login/**"),
                    mvcMatcherBuilder.pattern("/auth/**"),
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
            .exceptionHandling { it.authenticationEntryPoint(customAuthenticationEntryPoint) }
            .exceptionHandling { it.accessDeniedHandler(customAccessDeniedHandler) }
            .oauth2Login {
                it.loginPage("/login")
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
}