package org.example.common

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import javax.crypto.SecretKey

@Service
@RequiredArgsConstructor
class JwtService(
    private val redisService: RedisService,
    private val userRepository: UserRepository
) {
    fun extractLogin(token: String?): String {
        return extractClaim(token) { obj: Claims -> obj.subject }
    }
    fun <T> extractClaim(token: String?, claimResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimResolver.apply(claims)
    }
    fun generateToken(extraClaims: Map<String?, Any?>?, client: UserDetails): String {
        val token = Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(client.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 24 * 60))
            .signWith(signKey, SignatureAlgorithm.HS256)
            .compact()
        redisService!!.setToken(client.username, token)
        return token
    }
    fun GetUserIfTokenValid(login: String?, token: String): User? {
        val tk = redisService!!.getToken(login)
        return if (tk != null && tk == token && !isTokenExpired(tk)) {
            userRepository!!.findByLogin(login).get()
        } else null
    }
    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parser()
            .setSigningKey(signKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }
    private fun extractExpiration(token: String): Date {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }
    private val signKey: SecretKey
        private get() {
            val keyBytes = Decoders.BASE64.decode(SECRET)
            return Keys.hmacShaKeyFor(keyBytes)
        }
    companion object {
        private const val SECRET = "c3ltYm9sZXh0cmFoYXZlaGltc2VsZmV2ZW50dWFsbHl1bml0c3VwcG9ydGRldGFpbHA="
    }
}
