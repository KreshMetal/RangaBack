package com.Ranga.App.Repository

import com.Ranga.App.Entity.Comics
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ComicsRepository : JpaRepository<Comics, Long> {

    @Query("SELECT c FROM Comics c WHERE LOWER(c.name) = LOWER(:name) OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun findByNameStartingWithOrNameContaining(@Param("name") name: String): List<Comics>
}
