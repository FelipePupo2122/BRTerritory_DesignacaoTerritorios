package com.felipe.brterritory.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {
    BottomAppBar(
        modifier = Modifier.height(56.dp) // Define a altura do BottomAppBar
    ) {
        // Botão para voltar (à esquerda)
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.weight(1f, fill = true) // Deixa o botão da esquerda ocupar o espaço
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Voltar"
            )
        }

        // Botão para navegar à HomeScreen (à direita)
        IconButton(
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true } // Garante que a Home não acumule no stack de navegação
                }
            },
            modifier = Modifier.weight(1f, fill = true) // Deixa o botão da direita ocupar o espaço
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home"
            )
        }
    }
}
