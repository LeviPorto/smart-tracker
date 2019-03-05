package com.levi.smarttracker.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JWTUser(private val username: String, private val password: String, private val authorities: Collection<GrantedAuthority>) : UserDetails {

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun isEnabled(): Boolean = true

}
