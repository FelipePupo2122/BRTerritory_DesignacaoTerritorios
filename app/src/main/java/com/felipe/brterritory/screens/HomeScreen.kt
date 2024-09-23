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
        CarouselSection(
            title = "Territórios Alugados",
            items = listOf("Território 1", "Território 2", "Território 3"),
            onItemClick = { onViewRentedTerritoriesClick() }
        )

        CarouselSection(
            title = "Cadastrar Territórios",
            items = listOf("Território A", "Território B", "Território C"),
            onItemClick = { onRegisterTerritoryClick() }
        )

        CarouselSection(
            title = "Alugar Território",
            items = listOf("Território X", "Território Y", "Território Z"),
            onItemClick = { onRentTerritoryClick() }
        )

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
