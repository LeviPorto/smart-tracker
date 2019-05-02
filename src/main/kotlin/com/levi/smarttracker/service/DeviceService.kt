package com.levi.smarttracker.service

import com.levi.smarttracker.entitiy.Device
import com.levi.smarttracker.repository.DeviceRepository
import com.levi.smarttracker.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class DeviceService (private val repository : DeviceRepository, private val userRepository : UserRepository) {

    fun create(device : Device, userId : Int) : Device {
        val deviceByImei = repository.findByImei(device.imei)
        val loggedUser = userRepository.findById(userId)
        if(deviceByImei != null) repository.deleteById(deviceByImei.id!!)
        return repository.save(Device(device.imei, loggedUser.get()))
    }

}