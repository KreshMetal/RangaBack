package com.Ranga.App.Controller

import com.Ranga.App.Request.RatingRequest
import com.Ranga.App.Request.UserAndComicsRequest
import com.Ranga.App.Service.RatingService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/rating")
@RequiredArgsConstructor
class RatingController(
    private val ratingService: RatingService
) {

    @PostMapping("/rate")
    fun rate(@RequestBody request: RatingRequest): ResponseEntity<String>{
        ratingService.rate(request)
        return ResponseEntity.ok("Оценка успешно выставлена")
    }

    @DeleteMapping("/delete")
    fun deleteRating(@RequestBody request: UserAndComicsRequest): ResponseEntity<String>{
        ratingService.deleteRating(request)
        return ResponseEntity.ok("Оценка успешно удалена")
    }
}