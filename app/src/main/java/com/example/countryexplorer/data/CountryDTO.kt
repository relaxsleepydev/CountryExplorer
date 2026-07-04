package com.example.countryexplorer.data

data class CountryDTO(
    val name: String?,
    val capital: String?,
    val population: Long?,
    val media: MediaDTO?
)

data class MediaDTO(
    val flag: String?
)