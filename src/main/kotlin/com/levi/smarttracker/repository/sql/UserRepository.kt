package com.levi.smarttracker.repository.sql

import com.levi.smarttracker.entitiy.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository  : CrudRepository<User, Int>{

    fun findByUsername(username : String) : User?

}