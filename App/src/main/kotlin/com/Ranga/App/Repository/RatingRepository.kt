package com.Ranga.App.Repository

import com.Ranga.App.Entity.Rating
import com.Ranga.App.Entity.RatingId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RatingRepository : JpaRepository<Rating, RatingId> {

    fun save(rating: Rating): Rating

    fun findByUserLoginAndComicsId(userLogin: String, comicsId: Long): Rating?

    fun deleteByUserLoginAndComicsId(userLogin: String, comicsId: Long)

    @Query("SELECT r FROM Rating r WHERE r.comics.id = :comicsId")
    fun findAllByComicsId(@Param("comicsId") comicsId: Long): List<Rating>
}