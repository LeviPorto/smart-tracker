package com.levi.smarttracker.controller

import com.levi.smarttracker.document.Coordinate
import com.levi.smarttracker.dto.DeviceConverterDTO
import com.levi.smarttracker.dto.DeviceDTO
import com.levi.smarttracker.entitiy.Device
import com.levi.smarttracker.service.CoordinateService
import com.levi.smarttracker.service.DeviceService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/smartTracker/device")
class DeviceController(private val service : DeviceService, private val coordinateService: CoordinateService) {

    @PostMapping
    fun create(@RequestBody deviceDTO: DeviceDTO) : Device =
        service.create(DeviceConverterDTO.convertDTOIntoDevice(deviceDTO), deviceDTO.userId!!)


    @GetMapping("/{deviceId}/coordinates/findByDate/startDate/{startDate}/endDate/{endDate}")
    fun findCoordinatesByDeviceAndDate(@PathVariable deviceId : Int,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") startDate : Date,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") endDate : Date)
            : List<Coordinate>? = coordinateService.retrieveCoordinatesByDeviceAndDate(deviceId, startDate, endDate)

}