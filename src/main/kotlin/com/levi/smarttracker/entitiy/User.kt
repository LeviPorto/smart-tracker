package com.levi.smarttracker.entitiy

import com.levi.smarttracker.enumerated.PerfilEnum
import javax.persistence.*

@Entity
class User (
        @Column val username: String,
        @Column val password: String,
        @Column val email: String,
        @Enumerated(EnumType.STRING) @Column val perfil : PerfilEnum? = null,
        @Column @Id @GeneratedValue(strategy=GenerationType.IDENTITY) val id: Int? = null
)

