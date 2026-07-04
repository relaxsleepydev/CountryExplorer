package com.example.countryexplorer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CountryEntity::class],
    version = 1
)

// connecting dao and database (dto is not connected with database)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDAO

    // to ensure only one instance of database exists throughout the applications' lifetime
    companion object {
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getDatabase(context: Context): CountryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,
                    "country_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
