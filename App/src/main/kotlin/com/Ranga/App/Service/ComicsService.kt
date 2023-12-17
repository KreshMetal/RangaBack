package com.Ranga.App.Service

import com.Ranga.App.Entity.Comics
import com.Ranga.App.Repository.ComicsRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ComicsService(
    private val comicsRepository : ComicsRepository
) {
    fun createComics(comics: Comics): Long{
        return comicsRepository.save(comics).id!!
    }

    fun updateComics(comics: Comics){
        comicsRepository.save(comics)
    }

    fun deleteComics(comicsId: Long){
        comicsRepository.deleteById(comicsId)
    }

    fun getComics(comicsId: Long): Comics{
        return comicsRepository.getReferenceById(comicsId)
    }

    fun searchComics(name: String): List<Comics> {
        return comicsRepository.findByNameStartingWithOrNameContaining(name)
    }
}