package com.felipe.brterritory.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipe.brterritory.components.CarouselSection
import com.felipe.brterritory.ui.theme.BRTerritoryTheme


@Composable
fun HomeScreen(
    onRegisterTerritoryClick: () -> Unit,
    onRentTerritoryClick: () -> Unit,
    onViewRentedTerritoriesClick: () -> Unit,
    onRegisterLeaderClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carrossel de Territórios Alugados
        CarouselSection(
            title = "Territórios Alugados",
            items = listOf("Território 1", "Território 2", "Território 3"),
            onItemClick = { onViewRentedTerritoriesClick() }
        )

        // Carrossel de Cadastro de Territórios
        CarouselSection(
            title = "Cadastrar Territórios",
            items = listOf("Território A", "Território B", "Território C"),
            onItemClick = { onRegisterTerritoryClick() }
        )

        // Carrossel de Aluguel de Territórios
        CarouselSection(
            title = "Alugar Território",
            items = listOf("Território X", "Território Y", "Território Z"),
            onItemClick = { onRentTerritoryClick() }
        )

        // Carrossel de Cadastro de Dirigentes
        CarouselSection(
            title = "Cadastrar Dirigentes",
            items = listOf("Dirigente 1", "Dirigente 2", "Dirigente 3"),
            onItemClick = { onRegisterLeaderClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BRTerritoryTheme {
        HomeScreen(
            onRegisterTerritoryClick = {},
            onRentTerritoryClick = {},
            onViewRentedTerritoriesClick = {},
            onRegisterLeaderClick = {}
        )
    }
}
