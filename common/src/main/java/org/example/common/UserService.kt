package org.example.common

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(
    val repository: UserRepository
) {
    fun addUser(user: User) {
        repository.save(user)
    }

    fun getUser(login: String?): User? {
        val client = repository.findByLogin(login)
        return client.orElse(null)
    }
}
