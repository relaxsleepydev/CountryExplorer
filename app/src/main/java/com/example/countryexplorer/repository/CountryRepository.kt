package com.example.countryexplorer.repository

import com.example.countryexplorer.CountryApi
import com.example.countryexplorer.CountryApplication
import com.example.countryexplorer.data.CountryDAO
import com.example.countryexplorer.data.CountryDTO
import com.example.countryexplorer.data.CountryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


// fetches data from API(DTO), converts it into database format(Entities) and saves it into room
class CountryRepository(
    private val api: CountryApi,
    private val dao: CountryDAO
) {
    // 1. giving the app access to Room
    val countries: Flow<List<CountryEntity>> = dao.getAllCountries()

    // 2. taking data from DTO and putting it in room
    suspend fun refreshCountries() {
        withContext(Dispatchers.IO) {
            try {
                val networkDtos: List<CountryDTO> = api.getAllCountries()

                val entities: List<CountryEntity> = networkDtos.map { dto ->
                    CountryEntity(
                        name = dto.name ?: "Unknown Country",
                        capital = dto.capital ?: "N/A",
                        population = dto.population ?: 0L,
                        // If media is null OR flag is null, just use an empty string
                        flagUrl = dto.media?.flag ?: ""
                    )
                }
                dao.insertAll(entities)
                android.util.Log.d("MY_APP_DEBUG", "SUCCESS: SAVED TO DATABASE!")

            } catch (e: Exception) {
                // This ensures if it EVER fails again, it screams in red text
                android.util.Log.e("MY_APP_ERROR", "Crash reason: ${e.message}", e)
            }
        }
    }
}