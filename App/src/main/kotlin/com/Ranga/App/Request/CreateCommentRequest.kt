package com.Ranga.App.Request

data class CreateCommentRequest(
    val userLogin: String,
    val comicsId: Long,
    val text: String
)
