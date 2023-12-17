package com.Ranga.App.Controller

import com.Ranga.App.Entity.Comics
import com.Ranga.App.Entity.Constants
import com.Ranga.App.Service.ComicsService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api/comics")
@RequiredArgsConstructor
class ComicsController(
    val comicsService: ComicsService
) {
    @PostMapping("/add")
    fun createComics(@RequestBody comics: Comics): ResponseEntity<Long>{
        val id = comicsService.createComics(comics)
        return ResponseEntity.ok(id)
    }
    @GetMapping("/get")
    fun getComics(@RequestParam id: Long): ResponseEntity<Comics> {
        try {
            val comics = comicsService.getComics(id)
            return ResponseEntity.ok(comics)
        } catch (e: Exception) {
            return ResponseEntity(Constants.EMPTY_COMICS, HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/search")
    fun searchComics(@RequestParam name: String): ResponseEntity<List<Comics>> {
        return ResponseEntity.ok(comicsService.searchComics(name))
    }
    @PutMapping("/update")
    fun updateComics(@RequestBody comics: Comics): ResponseEntity<String>{
        try {
            comicsService.updateComics(comics)
            return ResponseEntity.ok("Комикс успешно обновлён")
        } catch (e: Exception) {
            return ResponseEntity("Комик не найден", HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete")
    fun deleteComics(@RequestParam id: Long): ResponseEntity<String>{
        try {
            comicsService.deleteComics(id)
            return ResponseEntity.ok("Комикс успешно удалён")
        } catch (e: Exception) {
            return ResponseEntity("Комик не найден", HttpStatus.NOT_FOUND)
        }
    }
}