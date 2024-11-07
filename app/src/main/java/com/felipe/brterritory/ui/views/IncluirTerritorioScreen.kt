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
fun IncluirTerritorioScreen(
    viewModel: TerritoriosViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var dirigenteId by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Novo Território",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dirigenteId,
            onValueChange = { dirigenteId = it },
            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("ID do Dirigente") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = androidx.compose.ui.graphics.Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            if (nome.isEmpty() || descricao.isEmpty() || dirigenteId.isEmpty()) {
                errorMessage = "Todos os campos são obrigatórios!"
            } else {
                coroutineScope.launch {
                    val novoTerritorio = Territorio(
                        nome = nome,
                        descricao = descricao
                    )
                    viewModel.gravarTerritorio(novoTerritorio)
                    navController.popBackStack()
                }
            }
        }) {
            Text(text = "Salvar", fontSize = 30.sp)
        }
    }
}

