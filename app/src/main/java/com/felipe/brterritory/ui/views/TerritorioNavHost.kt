package com.felipe.brterritory.ui.views

import EditarTerritorioScreen
import IncluirTerritorioScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipe.brterritory.ui.components.BottomBar
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel
import com.felipe.brterritory.ui.viewmodels.ModificacoesViewModel

@Composable
fun TerritorioNavHost(
    viewModel: TerritoriosViewModel,
    modificacaoViewModel: ModificacoesViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController)
            }
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
            composable("territorioPorDirigente") {
                TerritorioPorDirigenteScreen(viewModel, navController)
            }
            composable("historico") {
                HistModificacoesScreen(modificacaoViewModel)
            }
        }
    }
}
