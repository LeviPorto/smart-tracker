package com.levi.smarttracker.security

import java.util.Date
import java.util.HashMap

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

@Component
class JWTTokenUtil {

    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    val CLAIM_KEY_USERNAME = "sub"
    val CLAIM_KEY_ROLE = "role"
    val CLAIM_KEY_CREATED = "created"

    fun getUsernameFromToken(token: String): String? {
        var username: String?
        try {
            val claims = getClaimsFromToken(token)
            username = claims!!.getSubject()
        } catch (e: Exception) {
            username = null
        }

        return username
    }

    fun getExpirationDateFromToken(token: String): Date? {
        var expiration: Date?
        try {
            val claims = getClaimsFromToken(token)
            expiration = claims!!.getExpiration()
        } catch (e: Exception) {
            expiration = null
        }

        return expiration
    }

    fun refreshToken(token: String): String? {
        var refreshedToken: String?
        try {
            val claims = getClaimsFromToken(token)
            claims!!.put(CLAIM_KEY_CREATED, Date())
            refreshedToken = generateToken(claims)
        } catch (e: Exception) {
            refreshedToken = null
        }

        return refreshedToken
    }

    fun tokenValid(token: String): Boolean = !tokenExpired(token)

    fun getToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims[CLAIM_KEY_USERNAME] = userDetails.username
        userDetails.authorities.forEach { authority -> claims[CLAIM_KEY_ROLE] = authority.authority }
        claims[CLAIM_KEY_CREATED] = Date()

        return generateToken(claims)
    }

    private fun getClaimsFromToken(token: String): Claims? {
        var claims: Claims?
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()
        } catch (e: Exception) {
            claims = null
        }

        return claims
    }

    private fun generateDateExpiracao(): Date = Date(System.currentTimeMillis() + expiration!! * 1000)

    private fun tokenExpired(token: String): Boolean {
        val dateExpiration = this.getExpirationDateFromToken(token) ?: return false
        return dateExpiration.before(Date())
    }

    private fun generateToken(claims: Map<String, Any>): String =
        Jwts.builder().setClaims(claims).setExpiration(generateDateExpiracao())
                .signWith(SignatureAlgorithm.HS256, secret).compact()

}
