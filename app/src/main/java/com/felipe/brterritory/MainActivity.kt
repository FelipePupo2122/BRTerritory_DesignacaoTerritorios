// MainActivity.kt
package com.felipe.brterritory

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipe.brterritory.screens.*
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
