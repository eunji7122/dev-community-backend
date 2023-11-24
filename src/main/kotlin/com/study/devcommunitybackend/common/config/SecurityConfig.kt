package com.study.devcommunitybackend.common.config

import com.study.devcommunitybackend.common.authority.JwtAuthenticationFilter
import com.study.devcommunitybackend.common.authority.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector


@Configuration
@EnableMethodSecurity
class SecurityConfig (
        private val jwtTokenProvider: JwtTokenProvider,
) {

//    private val AUTH_WHITELIST = arrayOf(
//        "/img/**",
//        "/css/**"
//    )

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
                            mvcMatcherBuilder.pattern("/signUp"),
                            mvcMatcherBuilder.pattern("/signIn"),
                    ).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/member/info")).hasRole("MEMBER")
                        .requestMatchers(mvcMatcherBuilder.pattern("/boards")).hasRole("ADMIN")
//                    .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/profile/*")).permitAll()
                    .anyRequest().authenticated()
                }
                .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
                )

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
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}