package com.example.countryexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryexplorer.data.CountryEntity
import com.example.countryexplorer.repository.CountryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository): ViewModel() {

    val uiState: StateFlow<List<CountryEntity>> = repository.countries.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        // Automatically fetch fresh data from the internet when the screen opens
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            repository.refreshCountries()
        }
    }
}