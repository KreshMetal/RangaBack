package com.Ranga.App.Request

data class RatingRequest(
    val userLogin: String,
    val comicsId: Long,
    val rating: Int
)
