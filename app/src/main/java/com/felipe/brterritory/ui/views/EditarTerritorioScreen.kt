package com.felipe.brterritory.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel
import kotlinx.coroutines.launch

@Composable
fun EditarTerritorioScreen(
    viewModel: TerritoriosViewModel,
    territorioId: Int?,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var territorio: Territorio? by remember { mutableStateOf(null) }

    LaunchedEffect(territorioId) {
        coroutineScope.launch {
            if (territorioId != null) {
                territorio = viewModel.buscarTerritorioPorId(territorioId)
                territorio?.let {
                    nome = it.nome
                    descricao = it.descricao
                }
            }
        }
    }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Editar Território",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            coroutineScope.launch {
                val territorioEditado = Territorio(
                    id = territorioId,
                    nome = nome,
                    descricao = descricao
                )
                viewModel.gravarTerritorio(territorioEditado)
                navController.popBackStack()
            }
        }) {
            Text(text = "Salvar", fontSize = 30.sp)
        }
    }
}
