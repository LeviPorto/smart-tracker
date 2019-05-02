package com.levi.smarttracker.dto

import com.levi.smarttracker.document.Coordinate
import java.util.*

data class CoordinateDTO(
        val lat: Double,
        val lng: Double,
        val date: Date,
        val deviceId: Int,
        val id: String? = null
)

object CoordinateConverterDTO {

    fun convertDTOIntoCoordinate(coordinateDTO: CoordinateDTO): Coordinate {
        return Coordinate(coordinateDTO.lat, coordinateDTO.lng, coordinateDTO.date, coordinateDTO.deviceId)
    }

}