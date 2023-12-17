package com.Ranga.App.Service

import com.Ranga.App.Entity.Comics
import com.Ranga.App.Entity.Rating
import com.Ranga.App.Entity.RatingId
import com.Ranga.App.Repository.RatingRepository
import com.Ranga.App.Request.RatingRequest
import com.Ranga.App.Request.UserAndComicsRequest
import lombok.RequiredArgsConstructor
import org.example.common.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class RatingService(
    private val ratingRepository: RatingRepository,
    private val userService: UserService,
    private val comicsService: ComicsService
) {

    fun rate(ratingRequest: RatingRequest){
        val user = userService.getUser(ratingRequest.userLogin)
        val comics = comicsService.getComics(ratingRequest.comicsId)
        ratingRepository.save(Rating(
            RatingId(
                userLogin = user!!.login,
                comicsId = comics.id!!
            ),
            user, comics, ratingRequest.rating))
        updateScore(comics)
    }

    @Transactional
    fun deleteRating(userAndComicsRequest: UserAndComicsRequest){
        ratingRepository.deleteByUserLoginAndComicsId(
            userLogin = userAndComicsRequest.userLogin,
            comicsId = userAndComicsRequest.comicsId)
        updateScore(comicsService.getComics(userAndComicsRequest.comicsId))
    }

    private fun updateScore(comics:Comics){
        val allRatings = ratingRepository.findAllByComicsId(comics.id!!)
        val total = allRatings.sumOf { it.score }
        val count = allRatings.size
        if (count == 0) {
            comics.rating = 0.0
        }
        else {
            comics.rating = total.toDouble() / count
        }
        comicsService.updateComics(comics)
    }
}