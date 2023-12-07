package com.example.carrepo.repository

import androidx.lifecycle.LiveData
import com.example.carrepo.data.CarDao
import com.example.carrepo.data.FuelDao
import com.example.carrepo.model.CarData
import com.example.carrepo.model.FuelData
import kotlinx.coroutines.flow.Flow

class CarRepository(private val carDao: CarDao, private val fuelDao: FuelDao) {

    val readAllCars: LiveData<List<CarData>> = carDao.readAllCars()
    val readAllFuelInfo: LiveData<List<FuelData>> = fuelDao.readAllFuelData()

    suspend fun addCar(some: CarData) {
        carDao.insertCar(some)
    }

    suspend fun updateCar(some: CarData) {
        carDao.upsertCar(some)
    }

    suspend fun deleteCar(some: CarData) {
        carDao.deleteCar(some)
    }

    suspend fun deleteAllCars() {
        carDao.deleteAllCars()
    }

    suspend fun orderByNew() {
        carDao.orderByNew()
    }
    fun orderByOwnerName() {
        carDao.orderCarsByOwner()
    }

    //Fuel behaviour

    suspend fun addFuelInfo(some: FuelData) {
        fuelDao.upsertFuelInfo(some)
    }

    suspend fun updateFuelInfo(some: FuelData) {
        fuelDao.upsertFuelInfo(some)
    }

    suspend fun deleteFuelInfo(some: FuelData) {
        fuelDao.deleteFuelInfo(some)
    }

    suspend fun deleteAllFuelInfo() {
        fuelDao.deleteAllFuelData()
    }


}