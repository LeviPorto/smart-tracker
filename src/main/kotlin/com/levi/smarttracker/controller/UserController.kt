package com.levi.smarttracker.controller

import com.levi.smarttracker.dto.UserConverterDTO
import com.levi.smarttracker.dto.UserDTO
import com.levi.smarttracker.entitiy.User
import com.levi.smarttracker.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/smartTracker/user")
class UserController(private val service : UserService) {

    @PostMapping
    fun create(@RequestBody userDTO: UserDTO)
            : User? = service.create(UserConverterDTO.convertDTOIntoUser(userDTO))

}