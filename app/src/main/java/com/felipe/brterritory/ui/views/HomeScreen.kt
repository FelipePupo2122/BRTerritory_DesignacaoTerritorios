package com.felipe.brterritory.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var isEdit by remember { mutableStateOf(false) }
    var territoryId by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Título do aplicativo
        Text(
            text = "App BRTerritory",
            fontSize = 28.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 32.dp)
        )

        // Botões para navegar
        Column(
            modifier = Modifier.fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    isEdit = true
                    showDialog = true
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Editar Território")
            }

            Button(
                onClick = {
                    isEdit = false
                    showDialog = true
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Excluir Território")
            }

            Button(
                onClick = { navController.navigate("incluirTerritorio") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Incluir Território")
            }

            Button(
                onClick = { navController.navigate("listarTerritorios") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Listar Territórios")
            }

            Button(
                onClick = { navController.navigate("territorioPorDirigente") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Territórios por Dirigente")
            }
        }
    }

    // Pop-up de entrada de ID do Território
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = if (isEdit) "Editar Território" else "Excluir Território") },
            text = {
                Column {
                    Text("Informe o ID do Território:")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = territoryId,
                        onValueChange = { territoryId = it },
                        singleLine = true,
                        placeholder = { Text("ID do Território") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (territoryId.text.isNotBlank()) {
                            val route = if (isEdit) "editarTerritorio/${territoryId.text}" else "excluirTerritorio/${territoryId.text}"
                            navController.navigate(route)
                            showDialog = false
                        }
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
