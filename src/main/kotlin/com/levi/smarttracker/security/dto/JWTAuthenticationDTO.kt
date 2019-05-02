package com.levi.smarttracker.security.dto

data class JWTAuthenticationDTO(val username: String? = null,
                                val password: String? = null)
