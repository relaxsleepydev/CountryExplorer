package com.example.countryexplorer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val name: String,
    val capital: String,
    val population: Long,
    val flagUrl: String,
)
