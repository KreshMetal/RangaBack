package com.Ranga.App.Entity

import jakarta.persistence.*
import org.example.common.User
import java.io.Serializable

@Entity
@Table(name = "ratings")
data class Rating(
    @EmbeddedId
    val id: RatingId,

    @ManyToOne
    @MapsId("userLogin")
    @JoinColumn(name = "login", nullable = false)
    val user: User,

    @ManyToOne
    @MapsId("comicsId")
    @JoinColumn(name = "comics_id", nullable = false)
    val comics: Comics,

    @Column(nullable = false)
    val score: Int
)

@Embeddable
data class RatingId(
    @Column(name = "login")
    val userLogin: String,

    @Column(name = "comics_id")
    val comicsId: Long
) : Serializable
