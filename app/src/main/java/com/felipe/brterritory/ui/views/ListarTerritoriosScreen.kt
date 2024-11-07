package com.felipe.brterritory.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel

@Composable
fun ListarTerritoriosScreen(
    viewModel: TerritoriosViewModel,
    navController: NavController
) {
    val territorios by viewModel.territorios.collectAsState()

    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = "Territórios",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )
        territorios.forEach { territorio ->
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(text = territorio.nome, fontSize = 20.sp, modifier = Modifier.weight(1f))
                Button(onClick = {
                    navController.navigate("editarTerritorio/${territorio.id}")
                }) {
                    Text(text = "Editar")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("incluirTerritorio")
        }) {
            Text(text = "Adicionar Território", fontSize = 20.sp)
        }
    }
}
