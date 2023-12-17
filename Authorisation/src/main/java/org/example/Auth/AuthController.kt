package org.example.Auth

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest?): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.register(request!!))
    }

    @PostMapping("/autht")
    fun auth(@RequestBody request: AuthRequest?): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.auth(request!!))
    }
}
