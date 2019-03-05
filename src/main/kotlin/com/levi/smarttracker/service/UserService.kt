package com.levi.smarttracker.service

import com.levi.smarttracker.entitiy.User
import com.levi.smarttracker.enumerated.PerfilEnum
import com.levi.smarttracker.repository.sql.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {

    fun login(username : String, password : String) : User? {
        val user = repository.findByUsername(username)
        if(user != null && password == user.password) return user
        else throw Exception("Username or password are invalid!")
    }

    fun retriveByUsername(username: String): User? = repository.findByUsername(username)

    fun create(user : User): User? = repository.save(User(user.username,
            user.password, user.email, PerfilEnum.ROLE_USER))

}