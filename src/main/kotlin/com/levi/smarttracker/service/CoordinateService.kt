package com.levi.smarttracker.service

import com.levi.smarttracker.document.Coordinate
import com.levi.smarttracker.repository.nosql.CoordinateRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CoordinateService(private val repository : CoordinateRepository) {

    fun create(coordinate : Coordinate) : Coordinate = repository.save(coordinate)

    fun retrieveCoordinatesByDeviceAndDate(deviceId : Int, date : Date) : List<Coordinate>? = repository.findByDeviceIdAndDate(deviceId, date)

}