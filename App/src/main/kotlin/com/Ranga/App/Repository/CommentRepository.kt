package com.Ranga.App.Repository

import com.Ranga.App.Entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CommentRepository : JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.comics.id = :comicsId order by c.creationTime desc")
    fun findAllCommentsByComics(@Param("comicsId") comicsId : Long) : List<Comment>

    @Query("select c from Comment c where c.user.login = :userLogin order by c.creationTime desc")
    fun findAllCommentsByUser(@Param("userLogin") comicsId : String) : List<Comment>
}