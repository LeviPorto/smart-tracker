package com.levi.smarttracker.repository.nosql

import com.levi.smarttracker.document.Coordinate
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

import java.util.*

@Repository
class CoordinateRepository(val mongoTemplate: MongoTemplate) {

    val ONE_DAY_IN_SEC = 86400000

    fun findByDeviceIdAndDate(deviceId: Int, date: Date): List<Coordinate>? {
       val query = Query(Criteria
               .where("deviceId")
               .`is`(deviceId)
               .and("date")
               .gte(date)
               .lt(Date(date.time + ONE_DAY_IN_SEC)))
        return mongoTemplate.find(query, Coordinate::class.java)
    }

    fun save(coordinate: Coordinate) : Coordinate = mongoTemplate.save(coordinate)

}