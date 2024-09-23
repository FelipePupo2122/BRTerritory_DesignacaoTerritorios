package com.felipe.brterritory.navigation

sealed class AppRoutes(val route: String) {
    data object Home : AppRoutes("home")
    data object TerritoryList : AppRoutes("viewRentedTerritories")
    data object Profile : AppRoutes("profile")

    data object InitialScreen : AppRoutes("initialScreen")
    data object LoginScreen : AppRoutes("loginScreen")
    data object RegisterScreen : AppRoutes("registerScreen")
    data object RegisterTerritory : AppRoutes("registerTerritory")
    data object RentTerritory : AppRoutes("rentTerritory")
    data object ViewRentedTerritories : AppRoutes("viewRentedTerritories")
    data object RegisterLeader : AppRoutes("registerLeader")
}
