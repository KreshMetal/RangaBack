package com.Ranga.App.Auth

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.example.common.JwtService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
@RequiredArgsConstructor
class JWTAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        val jwt: String
        val login: String?
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        jwt = authHeader.substring(7)
        login = jwtService!!.extractLogin(jwt)
        if (login != null && SecurityContextHolder.getContext().authentication == null) {
            val client = jwtService.GetUserIfTokenValid(login, jwt)
            if (client != null) {
                val token = UsernamePasswordAuthenticationToken(
                    client,
                    null,
                    client.authorities
                )
                token.details = WebAuthenticationDetails(request)
                SecurityContextHolder.getContext().authentication = token
            } else {
                response.writer.println("Токен просрочен или неверный")
            }
        }
        filterChain.doFilter(request, response)
    }
}
