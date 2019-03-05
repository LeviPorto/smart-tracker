package com.levi.smarttracker.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import com.levi.smarttracker.service.UserService


@Service
class JWTUserDetailsService(private val userService: UserService) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.retriveByUsername(username)
        if (user != null) return JWTUserFactory.create(user)
        throw UsernameNotFoundException("Username not found.")
    }

}
