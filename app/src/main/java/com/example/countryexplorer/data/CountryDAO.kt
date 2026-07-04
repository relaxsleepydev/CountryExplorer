package com.example.countryexplorer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDAO {

    // fetch all rows from the countries table.
    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<CountryEntity>>

    // fetches a specific country by matching the name parameter.
    @Query("SELECT * FROM countries WHERE name = :name")
    fun getCountryByName(name: String): Flow<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity>)
}