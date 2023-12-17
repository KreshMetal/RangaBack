package com.Ranga.App.Controller

import com.Ranga.App.Entity.Constants
import com.Ranga.App.Service.ModeratorService
import lombok.RequiredArgsConstructor
import org.example.common.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/mod")
@RequiredArgsConstructor
class ModerController(
    val moderatorService: ModeratorService
) {

    @PostMapping("/ban")
    fun banUser(@RequestParam login: String): ResponseEntity<String>{
        try{
            moderatorService.banUser(login)
            return ResponseEntity<String>("Пользователь $login успешно забанен", HttpStatus.OK)
        } catch (e: Exception) {
            return ResponseEntity<String>("Пользователь $login не найден", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/unban")
    fun unbanUser(@RequestParam login: String): ResponseEntity<String>{
        try{
            moderatorService.unbanUser(login)
            return ResponseEntity<String>("Пользователь $login успешно разбанен", HttpStatus.OK)
        } catch (e: Exception) {
            return ResponseEntity<String>("Пользователь $login не найден", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/get")
    fun getUser(@RequestParam login: String): ResponseEntity<User>{
        try {
            val user = moderatorService.getUser(login)
            return ResponseEntity.ok(user)
        } catch (e: Exception) {
            return ResponseEntity(Constants.EMPTY_USER, HttpStatus.NOT_FOUND)
        }
    }
}