package com.levi.smarttracker.security

import java.util.ArrayList

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import com.levi.smarttracker.entitiy.User
import com.levi.smarttracker.enumerated.PerfilEnum


object JWTUserFactory {

    fun create(user: User): JWTUser = JWTUser(user.username, user.password, mapToGrantedAuthorities(user.perfil!!))

    private fun mapToGrantedAuthorities(userEnum: PerfilEnum): List<GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(userEnum.toString()))
        return authorities
    }

}
