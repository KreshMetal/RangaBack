package com.Ranga.App.Entity

import jakarta.persistence.*

@Entity
data class Comics(
    @Id
    @Column(name = "comics_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    val name: String,
    @Column(name = "description", nullable = false)
    val desc: String,
    @Column(nullable = false)
    val author: String,
    @Column(nullable = false)
    val url: String,
    var rating: Double = 0.0
)
