package com.example.myuniservices.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myuniservices.data.entities.ServiceEntity
import com.example.myuniservices.data.repository.ServiceRepository
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val repository: ServiceRepository
) : ViewModel()

{

    var services = mutableStateListOf<ServiceEntity>()
        private set

    // View services
    fun loadServices() {
        viewModelScope.launch {
            services.clear()
            services.addAll(repository.getAllServices())
        }
    }

    // Add new service

    fun addService(service: ServiceEntity) {
        viewModelScope.launch {
            repository.insertService(service)
            loadServices()   // refresh list
        }
    }

    //Delete Service
    fun clearAll() {
        viewModelScope.launch {
            repository.deleteAllServices()
            services.clear()
        }
    }





}
