package com.felipe.brterritory.navigation

sealed class AppRoutes(val route: String) {
    data object Home : AppRoutes("home")
    data object TerritoryList : AppRoutes("territory_list")
    data object Profile : AppRoutes("profile")
}
