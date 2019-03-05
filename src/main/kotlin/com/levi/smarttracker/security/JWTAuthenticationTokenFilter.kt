package com.levi.smarttracker.security

import java.io.IOException

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationTokenFilter : OncePerRequestFilter() {

    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Autowired
    private val jwtTokenUtil: JWTTokenUtil? = null

    private val AUTH_HEADER = "Authorization"
    private val BEARER_PREFIX = "Bearer "

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        if(request.getHeader(AUTH_HEADER) != null){
            var token: String? = request.getHeader(AUTH_HEADER)
            if (token != null && token.startsWith(BEARER_PREFIX)) {
                token = token.substring(7)
            }
            val username = jwtTokenUtil!!.getUsernameFromToken(token!!)

            if (username != null && SecurityContextHolder.getContext().authentication == null) {

                val userDetails = this.userDetailsService!!.loadUserByUsername(username)

                if (jwtTokenUtil.tokenValid(token)) {
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }

        }

        chain.doFilter(request, response)

    }

}
