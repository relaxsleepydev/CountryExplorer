package com.example.countryexplorer

import android.app.Application
import androidx.room.Room
import com.example.countryexplorer.data.CountryDatabase
import com.example.countryexplorer.repository.CountryRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// setting up the Room Database and Retrofit once, then passing them into the Repository.
class CountryApplication: Application() {
    lateinit var database: CountryDatabase
        private set

    lateinit var api: CountryApi
        private set

    lateinit var repository: CountryRepository
        private set

    override fun onCreate() {
        super.onCreate()

        // 1. Building the Room database using the application context
        database = Room.databaseBuilder(
            applicationContext,
            CountryDatabase::class.java,
            "country_database"
        ).build()

        // 2. Building the Retrofit API pointing to the stable server
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(CountryApi::class.java)

        // 3. Building the repository using the database and API
        repository = CountryRepository(api, database.countryDao())
    }
}