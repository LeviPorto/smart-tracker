package com.levi.smarttracker.security.service

import com.levi.smarttracker.security.factory.JWTUserFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import com.levi.smarttracker.service.UserService


@Service
class JWTUserDetailsService(private val userService: UserService) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return JWTUserFactory.create(userService.retrieveByUsername(username)!!)
    }

}
