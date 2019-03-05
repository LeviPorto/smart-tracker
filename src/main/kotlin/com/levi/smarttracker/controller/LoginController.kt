package com.levi.smarttracker.controller

import com.levi.smarttracker.entitiy.User
import com.levi.smarttracker.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/smartTracker")
class LoginController(private val service : UserService) {

    @GetMapping("/login")
    fun login(@RequestParam username : String, @RequestParam password : String)
            : User? = service.login(username, password)

}