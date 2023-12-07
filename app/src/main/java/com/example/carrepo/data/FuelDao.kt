package com.example.carrepo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.carrepo.model.FuelData
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelDao {

    @Upsert
    suspend fun upsertFuelInfo(fuelData: FuelData)

    @Delete
    suspend fun deleteFuelInfo(fuelData: FuelData)

    @Query("DELETE FROM fuel_table")
    suspend fun deleteAllFuelData()

    @Query("SELECT * FROM fuel_table ORDER BY date_fueled ASC")
    fun readAllFuelData(): LiveData<List<FuelData>>

    @Query("SELECT * FROM fuel_table ORDER BY fuel_quantity DESC")
    fun orderFuelByQuantity(): Flow<List<FuelData>>

    @Query("SELECT * FROM fuel_table ORDER BY fuel_type")
    fun orderFuelByType(): Flow<List<FuelData>>

}