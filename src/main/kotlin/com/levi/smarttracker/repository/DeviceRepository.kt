package com.levi.smarttracker.repository.sql

import com.levi.smarttracker.entitiy.Device
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : CrudRepository<Device, Int>{

    fun findByImei(imei: String): Device?

}