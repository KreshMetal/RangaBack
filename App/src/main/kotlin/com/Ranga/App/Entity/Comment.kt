package com.Ranga.App.Entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.example.common.User
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "login", nullable = false)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "comics_id", nullable = false)
    val comics: Comics,

    @Column(nullable = false)
    val creationTime: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false, length = 1000)
    val text: String
)