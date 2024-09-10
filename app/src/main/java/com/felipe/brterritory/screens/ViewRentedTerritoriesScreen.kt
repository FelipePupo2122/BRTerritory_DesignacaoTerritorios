// ViewRentedTerritoriesScreen.kt
package com.felipe.brterritory.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRentedTerritoriesScreen() {
    // Dummy data for rented territories
    val rentedTerritories = listOf(
        Territory("Território 1", "Dirigente A", "2024-09-01", "123456789"),
        Territory("Território 2", "Dirigente B", "2024-09-02", "987654321"),
        Territory("Território 3", "Dirigente C", "2024-09-03", "456789123")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Territórios Designados") })
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            rentedTerritories.forEach { territory ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = territory.name, style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Dirigente: ${territory.leader}")
                        Text(text = "Data de Aluguel: ${territory.rentDate}")
                        Text(text = "Telefone: ${territory.phoneNumber}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewRentedTerritoriesScreenPreview() {
    ViewRentedTerritoriesScreen()
}

data class Territory(
    val name: String,
    val leader: String,
    val rentDate: String,
    val phoneNumber: String
)
