package com.Ranga.App.Repository

import com.Ranga.App.Entity.FavoriteComics
import com.Ranga.App.Entity.FavoriteComicsId
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteComicsRepository :JpaRepository<FavoriteComics, FavoriteComicsId> {

    fun findAllByUserLogin(userLogin: String): List<FavoriteComics>
    fun deleteByUserLoginAndComicsId(userLogin: String, comicsId: Long)
}