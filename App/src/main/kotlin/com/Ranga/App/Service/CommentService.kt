package com.Ranga.App.Service

import com.Ranga.App.Entity.Comment
import com.Ranga.App.Repository.CommentRepository
import com.Ranga.App.Request.CreateCommentRequest
import com.Ranga.App.Request.UpdateCommentRequest
import lombok.RequiredArgsConstructor
import org.example.common.UserService
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CommentService(
    private val commentRepository: CommentRepository,
    val comicsService: ComicsService,
    val userService: UserService
) {

    fun createComment(createCommentRequest: CreateCommentRequest): Long{
        val comics = comicsService.getComics(createCommentRequest.comicsId)
        val user = userService.getUser(createCommentRequest.userLogin)
        val comment = Comment(
            comics = comics,
            user = user!!,
            text = createCommentRequest.text
        )
        return commentRepository.save(comment).id!!
    }

    fun getComment(id: Long): Comment = commentRepository.getReferenceById(id)

    fun updateComment(updateCommentRequest: UpdateCommentRequest){
        val comment = getComment(updateCommentRequest.id)
        commentRepository.save(Comment(
            id = comment.id,
            comics = comment.comics,
            user = comment.user,
            text = updateCommentRequest.text
        ))
    }

    fun deleteComment(id: Long) = commentRepository.deleteById(id)

    fun getAllByComics(comicsId: Long) = commentRepository.findAllCommentsByComics(comicsId)

    fun getAllByUser(login: String) = commentRepository.findAllCommentsByUser(login)

}