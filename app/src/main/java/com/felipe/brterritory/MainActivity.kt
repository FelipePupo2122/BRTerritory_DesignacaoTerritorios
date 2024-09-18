package com.felipe.brterritory

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipe.brterritory.screens.HomeScreen
import com.felipe.brterritory.screens.InitialScreen
import com.felipe.brterritory.screens.LoginScreen
import com.felipe.brterritory.screens.RegisterLeaderScreen
import com.felipe.brterritory.screens.RegisterScreen
import com.felipe.brterritory.screens.RegisterTerritoryScreen
import com.felipe.brterritory.screens.RentTerritoryScreen
import com.felipe.brterritory.screens.ViewRentedTerritoriesScreen
import com.felipe.brterritory.screens.util.BRBottomNavBar
import com.felipe.brterritory.screens.util.BRTopBar
import com.felipe.brterritory.ui.theme.BRTerritoryTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BRTerritoryTheme {
                MainScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            BRTopBar(
                currentPage = getCurrentPageName(navController.currentBackStackEntry?.destination?.route ?: ""),
                onPageSelected = { pageName ->
                    val route = when (pageName) {
                        "Home" -> "home"
                        "Register Territory" -> "registerTerritory"
                        "Rent Territory" -> "rentTerritory"
                        "View Rented Territories" -> "viewRentedTerritories"
                        "Register Leader" -> "registerLeader"
                        else -> return@BRTopBar
                    }
                    navController.navigate(route) {
                        // Optionally pop up to a specific route to clear the back stack
                        // popUpTo(route) { inclusive = true }
                    }
                }
            )
        },
        bottomBar = {
            BRBottomNavBar(navController = navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "initialScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("initialScreen") {
                InitialScreen(
                    onLoginClick = { navController.navigate("loginScreen") },
                    onRegisterClick = { navController.navigate("registerScreen") }
                )
            }
            composable("loginScreen") {
                LoginScreen(onLoginSuccess = { navController.navigate("home") })
            }
            composable("registerScreen") {
                RegisterScreen(onRegisterSuccess = { navController.navigate("home") })
            }
            composable("home") {
                HomeScreen(
                    onRegisterTerritoryClick = { navController.navigate("registerTerritory") },
                    onRentTerritoryClick = { navController.navigate("rentTerritory") },
                    onViewRentedTerritoriesClick = { navController.navigate("viewRentedTerritories") },
                    onRegisterLeaderClick = { navController.navigate("registerLeader") }
                )
            }
            composable("registerTerritory") { RegisterTerritoryScreen() }
            composable("rentTerritory") { RentTerritoryScreen() }
            composable("viewRentedTerritories") { ViewRentedTerritoriesScreen() }
            composable("registerLeader") { RegisterLeaderScreen() }
        }
    }
}

@Composable
fun getCurrentPageName(route: String): String {
    return when (route) {
        "home" -> "Home"
        "registerTerritory" -> "Register Territory"
        "rentTerritory" -> "Rent Territory"
        "viewRentedTerritories" -> "View Rented Territories"
        "registerLeader" -> "Register Leader"
        else -> "App Name"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
