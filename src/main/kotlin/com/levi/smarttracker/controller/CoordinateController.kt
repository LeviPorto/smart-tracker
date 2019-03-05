package com.levi.smarttracker.controller

import com.levi.smarttracker.document.Coordinate
import com.levi.smarttracker.dto.nosql.CoordinateConverterDTO
import com.levi.smarttracker.dto.nosql.CoordinateDTO
import com.levi.smarttracker.service.CoordinateService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/smartTracker/coordinate")
class CoordinateController(private val service : CoordinateService) {

    @PostMapping
    fun create(@RequestBody coordinateDTO: CoordinateDTO)
            : Coordinate = service.create(CoordinateConverterDTO.convertDTOIntoCoordinate(coordinateDTO))

}