package com.levi.smarttracker.security

import java.util.Optional

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = ["*"])
class AuthenticationController(private val userDetailsService: JWTUserDetailsService,
                               private val jwtTokenUtil: JWTTokenUtil) {

    private val log = LoggerFactory.getLogger(AuthenticationController::class.java)
    private val TOKEN_HEADER = "Authorization"
    private val BEARER_PREFIX = "Bearer "

    @PostMapping
    @Throws(AuthenticationException::class)
    fun generateTokenJwt(
            @Valid @RequestBody authenticationDto: JWTAuthenticationDTO, result: BindingResult): TokenDTO {
        log.info("Generating token for username {}.", authenticationDto.username)

        val userDetails = userDetailsService.loadUserByUsername(authenticationDto.username!!)
        val token = jwtTokenUtil.getToken(userDetails)

        return TokenDTO(token)
    }

    @PostMapping(value = "/refresh")
    fun generateRefreshTokenJwt(request: HttpServletRequest): TokenDTO {
        log.info("Generating refresh token JWT.")
        var token = Optional.ofNullable(request.getHeader(TOKEN_HEADER))

        if (token.isPresent && token.get().startsWith(BEARER_PREFIX))
            token = Optional.of(token.get().substring(7))

        val refreshedToken = jwtTokenUtil.refreshToken(token.get())
        return TokenDTO(refreshedToken!!)
    }

}
