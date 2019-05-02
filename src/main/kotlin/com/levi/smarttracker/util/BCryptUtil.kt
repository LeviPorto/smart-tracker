package com.levi.smarttracker.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object BCryptUtil {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun encode(password : String) : String {
        return passwordEncoder.encode(password)
    }

    fun match(password: String, encodedPassword : String) : Boolean{
        return passwordEncoder.matches(password, encodedPassword)
    }

}