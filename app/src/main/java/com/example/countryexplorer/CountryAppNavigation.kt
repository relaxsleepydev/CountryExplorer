package com.example.countryexplorer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countryexplorer.CountryApplication
import com.example.countryexplorer.Screen
import com.example.countryexplorer.viewmodel.CountryViewModel
import com.example.countryexplorer.viewmodel.CountryViewModelFactory

@Composable
fun CountryAppNavigation() {
    val navController = rememberNavController()

    // 1. Initializing the ViewModel here at the root level
    val context = LocalContext.current
    val app = context.applicationContext as CountryApplication

    // passing the factory here so the system knows how to build CountryViewModel
    val viewModel: CountryViewModel = viewModel(
        factory = CountryViewModelFactory(app.repository)
    )

    // 2. Building the navigation graph
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        // ROUTE 1: HOME SCREEN
        composable(
            Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onCountryClick = { clickedCountryName ->
                    navController.navigate(Screen.Detail.createRoute(clickedCountryName))
                }
            )
        }

        // ROUTE 2: DETAIL SCREEN
        composable(Screen.Detail.route) { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""

            // Now the router can successfully read the shared UI State
            val countries by viewModel.uiState.collectAsStateWithLifecycle()
            val selectedCountry = countries.find { it.name == countryName }

            if (selectedCountry != null) {
                DetailScreen(
                    country = selectedCountry,
                    onNavigateBack = { navController.popBackStack() }
                )
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}