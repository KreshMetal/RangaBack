package com.Ranga.App.Controller

import com.Ranga.App.Entity.Comment
import com.Ranga.App.Request.CreateCommentRequest
import com.Ranga.App.Request.UpdateCommentRequest
import com.Ranga.App.Service.CommentService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/comment")
@RequiredArgsConstructor
class CommentController(
    val commentService: CommentService
) {

    @PostMapping("/add")
    fun createComment(@RequestBody request: CreateCommentRequest): ResponseEntity<Long>{
        return ResponseEntity.ok(commentService.createComment(request))
    }

    @GetMapping("/get/comics")
    fun getAllByComics(@RequestParam id: Long): ResponseEntity<List<Comment>>{
        return ResponseEntity.ok(commentService.getAllByComics(id))
    }

    @GetMapping("/get/user")
    fun getAllByUser(@RequestParam login: String): ResponseEntity<List<Comment>>{
        return ResponseEntity.ok(commentService.getAllByUser(login))
    }

    @PutMapping("/update")
    fun updateComment(@RequestBody request: UpdateCommentRequest): ResponseEntity<String>{
        commentService.updateComment(request)
        return ResponseEntity.ok("Комментарий успешно обновлён")
    }

    @DeleteMapping("/delete")
    fun deleteComment(@RequestParam id: Long): ResponseEntity<String>{
        commentService.deleteComment(id)
        return ResponseEntity.ok("Комментарий успешно удалён")
    }
}