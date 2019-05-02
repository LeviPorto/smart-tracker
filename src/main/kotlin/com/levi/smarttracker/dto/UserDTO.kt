package com.levi.smarttracker.dto

import com.levi.smarttracker.entitiy.User
import com.levi.smarttracker.enumerated.PerfilEnum

data class UserDTO(
        val username: String,
        val password: String,
        val email: String,
        val perfil : PerfilEnum? = null,
        val id: Int? = null
)

object UserConverterDTO {

    fun convertDTOIntoUser(userDTO: UserDTO): User {
        return User(userDTO.username, userDTO.password, userDTO.email)
    }

}