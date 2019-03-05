package com.levi.smarttracker.entitiy

import javax.persistence.*

@Entity
data class Device(
        @Column val imei: String,
        @JoinColumn(name="id_user") @OneToOne val user: User? = null,
        @Column @Id  @GeneratedValue(strategy=GenerationType.IDENTITY) val id: Int? = null
)