package com.felipe.brterritory

import BRBottomNavBar
import BRTopBar
import SideMenu
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.felipe.brterritory.navigation.AppRoutes
import com.felipe.brterritory.screens.HomeScreen
import com.felipe.brterritory.screens.InitialScreen
import com.felipe.brterritory.screens.LoginScreen
import com.felipe.brterritory.screens.ProfileScreen
import com.felipe.brterritory.screens.RegisterLeaderScreen
import com.felipe.brterritory.screens.RegisterScreen
import com.felipe.brterritory.screens.RegisterTerritoryScreen
import com.felipe.brterritory.screens.RentTerritoryScreen
import com.felipe.brterritory.screens.ViewRentedTerritoriesScreen
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
    val showMenu = remember { mutableStateOf(false) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute !in listOf(AppRoutes.InitialScreen.route, AppRoutes.LoginScreen.route, AppRoutes.RegisterScreen.route)) {
                BRTopBar(
                    currentPage = getCurrentPageName(currentRoute ?: ""),
                    onMenuClick = { showMenu.value = !showMenu.value }
                )
            }
        },
        bottomBar = {
            if (currentRoute !in listOf(AppRoutes.InitialScreen.route, AppRoutes.LoginScreen.route, AppRoutes.RegisterScreen.route)) {
                BRBottomNavBar(navController = navController)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.InitialScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoutes.InitialScreen.route) {
                InitialScreen(
                    onLoginClick = { navController.navigate(AppRoutes.LoginScreen.route) },
                    onRegisterClick = { navController.navigate(AppRoutes.RegisterScreen.route) }
                )
            }
            composable(AppRoutes.LoginScreen.route) {
                LoginScreen(onLoginSuccess = { navController.navigate(AppRoutes.Home.route) })
            }
            composable(AppRoutes.RegisterScreen.route) {
                RegisterScreen(onRegisterSuccess = { navController.navigate(AppRoutes.Home.route) })
            }
            composable(AppRoutes.Home.route) {
                HomeScreen(
                    onRegisterTerritoryClick = { navController.navigate(AppRoutes.RegisterTerritory.route) },
                    onRentTerritoryClick = { navController.navigate(AppRoutes.RentTerritory.route) },
                    onViewRentedTerritoriesClick = { navController.navigate(AppRoutes.ViewRentedTerritories.route) },
                    onRegisterLeaderClick = { navController.navigate(AppRoutes.RegisterLeader.route) }
                )
            }
            composable(AppRoutes.RegisterTerritory.route) { RegisterTerritoryScreen() }
            composable(AppRoutes.RentTerritory.route) { RentTerritoryScreen() }
            composable(AppRoutes.ViewRentedTerritories.route) { ViewRentedTerritoriesScreen() }
            composable(AppRoutes.RegisterLeader.route) { RegisterLeaderScreen() }
            composable(AppRoutes.Profile.route) { ProfileScreen() }
        }

        // Menu Lateral
        if (showMenu.value) {
            SideMenu(onDismiss = { showMenu.value = false }) { pageName ->
                when (pageName) {
                    "Home" -> navController.navigate(AppRoutes.Home.route)
                    "Register Territory" -> navController.navigate(AppRoutes.RegisterTerritory.route)
                    "Rent Territory" -> navController.navigate(AppRoutes.RentTerritory.route)
                    "View Rented Territories" -> navController.navigate(AppRoutes.ViewRentedTerritories.route)
                    "Register Leader" -> navController.navigate(AppRoutes.RegisterLeader.route)
                    "Profile" -> navController.navigate(AppRoutes.Profile.route)
                }
                showMenu.value = false
            }
        }
    }
}






@Composable
fun getCurrentPageName(route: String): String {
    return when (route) {
        AppRoutes.Home.route -> "Home"
        AppRoutes.RegisterTerritory.route -> "Register Territory"
        AppRoutes.RentTerritory.route -> "Rent Territory"
        AppRoutes.ViewRentedTerritories.route -> "View Rented Territories"
        AppRoutes.RegisterLeader.route -> "Register Leader"
        AppRoutes.Profile.route -> "Profile"
        else -> "App Name"
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
