package com.levi.smarttracker.document

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.Id

@Document
data class Coordinate(
        val lat: Double,
        val lng: Double,
        val date: Date,
        val deviceId: Int,
        @Id val id: String? = null
)
