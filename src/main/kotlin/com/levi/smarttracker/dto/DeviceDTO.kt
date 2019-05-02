package com.levi.smarttracker.dto

import com.levi.smarttracker.entitiy.Device

data class DeviceDTO(
        val imei: String,
        val userId: Int? = null,
        val id: Int? = null
)

object DeviceConverterDTO {

    fun convertDTOIntoDevice(deviceDTO: DeviceDTO): Device {
        return Device(deviceDTO.imei)
    }

}