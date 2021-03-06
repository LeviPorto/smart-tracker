package com.levi.smarttracker.security.controller

import com.levi.smarttracker.security.util.JWTTokenUtil
import com.levi.smarttracker.security.dto.JWTAuthenticationDTO
import com.levi.smarttracker.security.dto.TokenDTO
import com.levi.smarttracker.security.service.JWTUserDetailsService
import com.levi.smarttracker.service.UserService
import com.levi.smarttracker.util.BCryptUtil.match
import java.util.Optional

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = ["*"])
class AuthenticationController(private val jwtUserDetailsService: JWTUserDetailsService,
                               private val jwtTokenUtil: JWTTokenUtil,
                               private val userService: UserService) {

    private val log = LoggerFactory.getLogger(AuthenticationController::class.java)
    private val TOKEN_HEADER = "Authorization"
    private val BEARER_PREFIX = "Bearer "

    @PostMapping
    @Throws(AuthenticationException::class)
    fun generateTokenJwt(
            @Valid @RequestBody authenticationDto: JWTAuthenticationDTO): TokenDTO? {
        log.info("Generating token for username {}.", authenticationDto.username)
        val user = userService.retrieveByUsername(authenticationDto.username!!)
                ?: throw UsernameNotFoundException("Username not found.")
        if(!match(user.password, authenticationDto.password!!)) return null
        val userDetails = jwtUserDetailsService.loadUserByUsername(user.username)
        val token = jwtTokenUtil.getToken(userDetails)

        return TokenDTO(token, user.id!!)
    }

    @PostMapping("/refresh")
    fun generateRefreshTokenJwt(request: HttpServletRequest): TokenDTO {
        log.info("Generating refresh token JWT.")
        var token = Optional.ofNullable(request.getHeader(TOKEN_HEADER))

        if (token.isPresent && token.get().startsWith(BEARER_PREFIX))
            token = Optional.of(token.get().substring(7))

        val refreshedToken = jwtTokenUtil.refreshToken(token.get())
        return TokenDTO(refreshedToken!!, null)
    }

}
