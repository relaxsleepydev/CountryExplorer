package com.example.countryexplorer.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
// NOTE: Make sure these imports match your actual folder structure!
import com.example.countryexplorer.CountryApplication
import com.example.countryexplorer.viewmodel.CountryViewModel
import com.example.countryexplorer.viewmodel.CountryViewModelFactory

@Composable
fun HomeScreen(
    viewModel: CountryViewModel, // <-- Accept the ViewModel from the parent wrapper
    onCountryClick: (String) -> Unit
) {
    // REMOVED: Local application context and factory setup lines are gone!

    // 1. Observe the database state directly
    val countries by viewModel.uiState.collectAsStateWithLifecycle()

    // 2. Setup the search state
    var searchText by remember { mutableStateOf("") }

    // 3. Filter the list safely without freezing the UI
    val filteredCountries = remember(searchText, countries) {
        countries.filter { country ->
            country.name.contains(searchText, ignoreCase = true)
        }
    }

    // 4. Assemble the UI
    Scaffold(
        topBar = {
            CountryTopBar(
                searchQuery = searchText,
                onSearchQueryChange = { newText -> searchText = newText }
            )
        }
    ) { innerPadding ->
        // Total countries count overlay for system tracking
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            androidx.compose.material3.Text(
                text = "TOTAL COUNTRIES: ${filteredCountries.size}",
                style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredCountries) { country ->
                    CountryCard(
                        country = country,
                        onClick = { onCountryClick(country.name) }
                    )
                }
            }
        }
    }
}