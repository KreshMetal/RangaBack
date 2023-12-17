package org.example.common

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
    @Id
    val login: String,
    @Column(name = "password")
    val pass: String,
    val name: String,
    val email: String,
    @Enumerated(EnumType.STRING)
    var role: Role,
    var banned : Boolean = false,
    ) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return setOf(
            SimpleGrantedAuthority(
                role.name
            )
        )
    }
    override fun getPassword(): String {
        return pass
    }
    override fun getUsername(): String {
        return login
    }
    override fun isAccountNonExpired(): Boolean {
        return true
    }
    override fun isAccountNonLocked(): Boolean {
        return !banned
    }
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
    override fun isEnabled(): Boolean {
        return true
    }
}
