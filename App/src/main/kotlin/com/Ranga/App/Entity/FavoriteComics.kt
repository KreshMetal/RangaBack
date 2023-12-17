package com.Ranga.App.Entity

import jakarta.persistence.*
import org.example.common.User
import java.io.Serializable

@Entity
@Table(name = "favorite_comics")
data class FavoriteComics(
    @EmbeddedId
    val id: FavoriteComicsId,

    @ManyToOne
    @MapsId("userLogin")
    @JoinColumn(name = "login", nullable = false)
    val user: User,

    @ManyToOne
    @MapsId("comicsId")
    @JoinColumn(name = "comics_id", nullable = false)
    val comics: Comics
)

@Embeddable
data class FavoriteComicsId(

    @Column(name = "login")
    val userLogin: String,

    @Column(name = "comics_id")
    val comicsId: Long
) : Serializable
