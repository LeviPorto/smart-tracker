package com.levi.smarttracker.security

data class JWTAuthenticationDTO(val username: String? = null,
                                val password: String? = null)
