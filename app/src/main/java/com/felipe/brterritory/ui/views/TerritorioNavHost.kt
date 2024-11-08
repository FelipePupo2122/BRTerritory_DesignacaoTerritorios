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
        composable("editarTerritorio/{territorioId}") { backStackEntry ->
            val territorioId = backStackEntry.arguments?.getString("territorioId")?.toInt()
            territorioId?.let {
                EditarTerritorioScreen(viewModel, it, navController)
            }
        }
        composable("excluirTerritorio/{territorioId}") { backStackEntry ->
            val territorioId = backStackEntry.arguments?.getString("territorioId")?.toInt()
            territorioId?.let {
                ExcluirTerritorioScreen(viewModel, it, navController)
            }
        }
    }
}
