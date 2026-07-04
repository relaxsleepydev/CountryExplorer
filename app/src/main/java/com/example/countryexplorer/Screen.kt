package com.example.countryexplorer

sealed class Screen(val route: String) {

    object Home: Screen("Home")

    object Detail: Screen("Detail/{countryName}") {
        fun createRoute(countryName: String) = "detail/$countryName"
    }
}