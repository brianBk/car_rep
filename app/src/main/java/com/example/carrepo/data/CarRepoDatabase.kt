package com.example.carrepo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carrepo.model.CarData
import com.example.carrepo.model.FuelData
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [CarData::class, FuelData::class], version = 1)
abstract class CarRepoDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
    abstract fun fuelDao(): FuelDao

    companion object {

        @Volatile
        private var INSTANCE: CarRepoDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): CarRepoDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, CarRepoDatabase::class.java, "carRepo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}