package org.example.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService {
    @Autowired
    private val redisTemplate: RedisTemplate<String, String>? = null
    fun setToken(login: String, token: String) {
        redisTemplate!!.opsForValue()[login] = token
    }

    fun getToken(login: String?): String {
        return redisTemplate!!.opsForValue()[login]
    }
}
