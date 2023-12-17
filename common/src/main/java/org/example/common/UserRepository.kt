package org.example.common

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User?, String?> {
    fun findByLogin(login: String?): Optional<User>
}
