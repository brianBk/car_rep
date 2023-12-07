package com.example.carrepo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "car_table")
data class CarData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "number_plate") val numberPlate: String,
    @ColumnInfo(name = "car_make") val carMake: String,
    @ColumnInfo(name = "car_model") val carModel: String,
    @ColumnInfo(name = "car_owner") val carOwner: String,
    @ColumnInfo(name = "car_tare") val carTare: String,
    @ColumnInfo(name = "car_weight") val carWeight: String,
    @ColumnInfo(name = "date_purchased") val datePurchased: String


)
