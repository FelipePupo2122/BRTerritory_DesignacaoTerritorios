package com.felipe.brterritory.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipe.brterritory.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Close

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRegisterTerritoryClick: () -> Unit = {},
    onRentTerritoryClick: () -> Unit = {},
    onViewRentedTerritoriesClick: () -> Unit = {},
    onRegisterLeaderClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var drawerState by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = { drawerState = !drawerState }) {
                        Icon(
                            imageVector = if (drawerState) Icons.Filled.Close else Icons.Filled.Menu,
                            contentDescription = if (drawerState) "Close menu" else "Open menu"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF6200EE))
            )
        },
        containerColor = Color.White // Define a cor de fundo padrão
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Conteúdo principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
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

            // Menu lateral
            if (drawerState) {
                DrawerMenu(
                    onLogoutClick = {
                        onLogoutClick()
                        drawerState = false
                    },
                    onProfileClick = {
                        onProfileClick()
                        drawerState = false
                    }
                )
            }
        }
    }
}

@Composable
fun DrawerMenu(
    onLogoutClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6200EE))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
                .padding(16.dp)
                .background(Color.White)
        ) {
            TextButton(onClick = onProfileClick) {
                Text("Meu Perfil", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = onLogoutClick) {
                Text("Sair", color = Color.Black)
            }
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
