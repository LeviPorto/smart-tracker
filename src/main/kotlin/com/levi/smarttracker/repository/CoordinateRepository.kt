package com.levi.smarttracker.repository

import com.levi.smarttracker.document.Coordinate
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

import java.util.*

@Repository
interface CoordinateRepository : MongoRepository<Coordinate, String> {

    fun findByDeviceIdAndDateBetween(deviceId: Int, startDate: Date, endDate : Date): List<Coordinate>?

}