package com.example.countryexplorer

import com.example.countryexplorer.data.CountryDTO
import retrofit2.http.GET

interface CountryApi {
    @GET("countries/countries")
    suspend fun getAllCountries(): List<CountryDTO>
}