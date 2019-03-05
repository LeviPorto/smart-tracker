package com.levi.smarttracker.service

import com.levi.smarttracker.entitiy.Device
import com.levi.smarttracker.repository.sql.DeviceRepository
import com.levi.smarttracker.repository.sql.UserRepository
import org.springframework.stereotype.Service

@Service
class DeviceService (private val repository : DeviceRepository, private val userRepository : UserRepository) {

    fun create(device : Device, userId : Int) {
        val deviceByImei = repository.findByImei(device.imei)
        val loggedUser = userRepository.findById(userId)
        if(deviceByImei != null) repository.deleteById(deviceByImei.id!!)
        repository.save(Device(device.imei, loggedUser.get()))
    }

}