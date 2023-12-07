package com.example.carrepo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.carrepo.data.CarRepoDatabase
import com.example.carrepo.model.CarData
import com.example.carrepo.model.FuelData
import com.example.carrepo.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CarViewModel(application: Application): AndroidViewModel(application) {

    val readAllCars: LiveData<List<CarData>>
    val readAllFuel: LiveData<List<FuelData>>

    private val repository: CarRepository

    init {
        val carDao = CarRepoDatabase.getDatabase(application).carDao()
        val fuelDao = CarRepoDatabase.getDatabase(application).fuelDao()
        repository = CarRepository(carDao, fuelDao)
        readAllCars = repository.readAllCars
        readAllFuel = repository.readAllFuelInfo
    }

    fun addCar(some: CarData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCar(some)
        }
    }
    fun updateCar(some: CarData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCar(some)
        }
    }
    fun deleteCar(some: CarData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCar(some)
        }
    }
    fun deleteAllCars() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCars()
        }
    }
    fun orderByNew() {
        viewModelScope.launch {
            repository.orderByNew()
        }
    }
    fun orderByOwner() {
        repository.orderByOwnerName()
    }

    //fuel functions

    fun addFuelInfo(some: FuelData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFuelInfo(some)
        }
    }
    fun updateFuelInfo(some: FuelData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFuelInfo(some)
        }
    }
    fun deleteFuelIfo(some: FuelData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFuelInfo(some)
        }
    }
    fun deleteAllFuelInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFuelInfo()
        }
    }
}