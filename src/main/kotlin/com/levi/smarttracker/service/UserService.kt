package com.levi.smarttracker.service

import com.levi.smarttracker.entitiy.User
import com.levi.smarttracker.enumerated.PerfilEnum
import com.levi.smarttracker.repository.UserRepository
import com.levi.smarttracker.util.BCryptUtil.encode
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun retrieveByUsername(username: String): User? = repository.findByUsername(username)

    fun create(user : User): User? = repository.save(User(user.username,
            encode(user.password), user.email, PerfilEnum.ROLE_USER))

}