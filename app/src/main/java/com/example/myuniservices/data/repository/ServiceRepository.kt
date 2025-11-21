package com.example.myuniservices.data.repository

import com.example.myuniservices.data.dao.ServiceDao
import com.example.myuniservices.data.entities.ServiceEntity

class ServiceRepository(
    private val serviceDao: ServiceDao
)

{

    suspend fun insertService(service: ServiceEntity) {
        serviceDao.insertService(service)
    }

    suspend fun getAllServices(): List<ServiceEntity> {
        return serviceDao.getAllServices()
    }

    suspend fun deleteAllServices() {
        serviceDao.deleteAll()
    }
}
