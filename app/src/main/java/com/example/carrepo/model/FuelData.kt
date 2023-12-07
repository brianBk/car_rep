package com.example.carrepo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fuel_table")
data class FuelData(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date_fueled") val date: String,
    @ColumnInfo(name = "car_type") val carType: String,
    @ColumnInfo(name = "fuel_quantity") val fuelQuantity: Double,
    @ColumnInfo(name = "fuel_type") val fuelType: String,
    @ColumnInfo(name = "fuel_price") val price: Double,
    @ColumnInfo(name = "total_amount") val total: Double
)
