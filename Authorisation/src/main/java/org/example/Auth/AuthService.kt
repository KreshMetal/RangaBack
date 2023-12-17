package org.example.Auth

import lombok.RequiredArgsConstructor
import org.example.common.JwtService
import org.example.common.Role
import org.example.common.User
import org.example.common.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthService(
    val userService: UserService,
    val passwordEncoder: PasswordEncoder,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager
) {
    fun register(request: RegisterRequest): AuthResponse {
        val user = User(
            login = request.login,
            email = request.email,
            pass = passwordEncoder.encode(request.password),
            name = request.name,
            banned = false,
            role = Role.CLIENT
        )
        if (userService.getUser(user.login) == null) {
            val token = jwtService.generateToken(HashMap(), user)
            userService.addUser(user)
            return AuthResponse(token)
        }
        return AuthResponse("Такой пользователь уже загеристрирован")
    }

    fun auth(request: AuthRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.login,
                request.password
            )
        )
        val user = userService.getUser(request.login)
        if (user != null) {
            val token = jwtService.generateToken(HashMap(), user)
            return AuthResponse(token)
        }
        return AuthResponse("Не найден такой пользователь")
    }
}

