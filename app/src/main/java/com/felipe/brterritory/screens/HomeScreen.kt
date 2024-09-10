// HomeScreen.kt
package com.felipe.brterritory.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipe.brterritory.R

@Composable
fun HomeScreen(
    onRegisterTerritoryClick: () -> Unit = {},
    onRentTerritoryClick: () -> Unit = {},
    onViewRentedTerritoriesClick: () -> Unit = {},
    onRegisterLeaderClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF6200EE)) {
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { /* Navegar para Home */ }
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_register), contentDescription = "Cadastrar") },
                    label = { Text("Cadastrar") },
                    selected = false,
                    onClick = onRegisterTerritoryClick
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_rent), contentDescription = "Alugar") },
                    label = { Text("Alugar") },
                    selected = false,
                    onClick = onRentTerritoryClick
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_leader), contentDescription = "Dirigentes") },
                    label = { Text("Dirigentes") },
                    selected = false,
                    onClick = onRegisterLeaderClick
                )
                // Novo Item: Territórios Alugados
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_rented), contentDescription = "Territórios Alugados") },
                    label = { Text("Alugados") },
                    selected = false,
                    onClick = onViewRentedTerritoriesClick
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Carrossel de Territórios Alugados
            CarouselSection(
                title = "Territórios Alugados",
                items = listOf("Território 1", "Território 2", "Território 3"),
                onItemClick = onViewRentedTerritoriesClick
            )

            // Carrossel de Cadastro de Territórios
            CarouselSection(
                title = "Cadastrar Territórios",
                items = listOf("Território A", "Território B", "Território C"),
                onItemClick = onRegisterTerritoryClick
            )

            // Carrossel de Aluguel de Territórios
            CarouselSection(
                title = "Alugar Território",
                items = listOf("Território X", "Território Y", "Território Z"),
                onItemClick = onRentTerritoryClick
            )

            // Carrossel de Cadastro de Dirigentes
            CarouselSection(
                title = "Cadastrar Dirigentes",
                items = listOf("Dirigente 1", "Dirigente 2", "Dirigente 3"),
                onItemClick = onRegisterLeaderClick
            )
        }
    }
}

@Composable
fun CarouselSection(
    title: String,
    items: List<String>,
    onItemClick: () -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
        LazyRow {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp),
                    onClick = onItemClick
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
