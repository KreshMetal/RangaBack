package com.Ranga.App.Service

import lombok.RequiredArgsConstructor
import org.example.common.User
import org.example.common.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ModeratorService(
    private val userRepository: UserRepository
) {

    fun banUser(login: String){
        val userOptional = userRepository.findByLogin(login)
        if (!userOptional.isPresent){
            throw NotFoundException()
        }
        val user = userOptional.get()
        user.banned = true
        userRepository.save(user)
    }

    fun unbanUser(login: String){
        val userOptional = userRepository.findByLogin(login)
        if (!userOptional.isPresent){
            throw NotFoundException()
        }
        val user = userOptional.get()
        user.banned = false
        userRepository.save(user)
    }

    fun getUser(login: String): User{
        val userOptional = userRepository.findByLogin(login)
        if (!userOptional.isPresent){
            throw NotFoundException()
        }
        return userOptional.get()
    }
}