package com.example.carrepo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.carrepo.model.CarData
import kotlinx.coroutines.flow.Flow


@Dao
interface CarDao {

    @Upsert
    suspend fun upsertCar(carData: CarData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carData: CarData)

    @Delete
    suspend fun deleteCar(carData: CarData)

    @Query("SELECT * FROM car_table ORDER BY id ASC")
    fun readAllCars(): LiveData<List<CarData>>

    @Query("SELECT * FROM car_table ORDER BY car_make ASC")
    fun orderCarsByMake(): LiveData<List<CarData>>

    @Query("SELECT * FROM car_table ORDER BY car_model DESC")
    fun orderByModel(): Flow<List<CarData>>

    @Query("SELECT * FROM car_table ORDER BY car_owner ASC")
    fun orderCarsByOwner(): Flow<List<CarData>>

    @Query("SELECT * FROM car_table ORDER BY date_purchased DESC")
    fun orderByNew(): Flow<List<CarData>>

    @Query("DELETE FROM car_table")
    suspend fun deleteAllCars()
}