package com.Ranga.App.Controller

import com.Ranga.App.Entity.Comics
import com.Ranga.App.Request.UserAndComicsRequest
import com.Ranga.App.Service.FavoriteComicsService
import lombok.RequiredArgsConstructor
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
class FavoriteController(
    private val favoriteComicsService: FavoriteComicsService
) {

    @PostMapping("/add")
    fun addToFavorite(@RequestBody request: UserAndComicsRequest): ResponseEntity<String>{
        favoriteComicsService.createFavorite(request)
        return ResponseEntity.ok("Комикс добавлен в избранное")
    }

    @GetMapping("/get")
    fun getFavorites(@RequestParam login: String): ResponseEntity<List<Comics>> {
        val comics = favoriteComicsService.getFavorites(login)
        return ResponseEntity.ok(comics)
    }

    @DeleteMapping("/delete")
    fun deleteFromFavorites(@RequestBody request: UserAndComicsRequest): ResponseEntity<String>{
        favoriteComicsService.deleteFavorite(request)
        return ResponseEntity.ok("Комикс удалён из избранного")
    }
}