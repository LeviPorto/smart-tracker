package com.levi.smarttracker.security

import java.io.IOException

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JWTAuthenticationEntryPoint : AuthenticationEntryPoint {

    @Throws(IOException::class)
    override fun commence(request: HttpServletRequest, response: HttpServletResponse,
                          authException: AuthenticationException) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acess denied!")
    }

}
