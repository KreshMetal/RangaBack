package com.Ranga.App.Service

import com.Ranga.App.Entity.Comics
import com.Ranga.App.Entity.FavoriteComics
import com.Ranga.App.Entity.FavoriteComicsId
import com.Ranga.App.Repository.FavoriteComicsRepository
import com.Ranga.App.Request.UserAndComicsRequest
import lombok.RequiredArgsConstructor
import org.example.common.UserService
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class FavoriteComicsService(
    val favoriteComicsRepository: FavoriteComicsRepository,
    val comicsService: ComicsService,
    val userService: UserService
) {

    fun createFavorite(userAndComicsRequest: UserAndComicsRequest){
        val comics = comicsService.getComics(userAndComicsRequest.comicsId)
        val user = userService.getUser(userAndComicsRequest.userLogin)
        val favoriteComics = FavoriteComics(FavoriteComicsId(
            userLogin = userAndComicsRequest.userLogin,
            comicsId = userAndComicsRequest.comicsId
        ), comics = comics, user = user!!)
        favoriteComicsRepository.save(favoriteComics)
    }

    fun getFavorites(userLogin: String): List<Comics>{
        val favorites = favoriteComicsRepository.findAllByUserLogin(userLogin)
        return favorites.map { it.comics }
    }

    fun deleteFavorite(userAndComicsRequest: UserAndComicsRequest){
        favoriteComicsRepository.deleteById(FavoriteComicsId(
            userLogin = userAndComicsRequest.userLogin,
            comicsId = userAndComicsRequest.comicsId))
    }
}