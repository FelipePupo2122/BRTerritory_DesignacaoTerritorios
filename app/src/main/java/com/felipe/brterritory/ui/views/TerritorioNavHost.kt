package com.felipe.brterritory.ui.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel

@Composable
fun TerritorioNavHost(
    viewModel: TerritoriosViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "listarTerritorios"
    ) {
        composable("listarTerritorios") {
            ListarTerritoriosScreen(viewModel, navController)
        }
        composable("incluirTerritorio") {
            IncluirTerritorioScreen(viewModel, navController)
        }
        composable("editarTerritorio/{territorioId}") { request ->
            val territorioId = request.arguments?.getString("territorioId")
            EditarTerritorioScreen(viewModel, territorioId?.toInt(), navController)
        }
    }
}
