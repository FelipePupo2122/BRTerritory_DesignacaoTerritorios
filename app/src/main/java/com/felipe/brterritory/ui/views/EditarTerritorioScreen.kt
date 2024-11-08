package com.felipe.brterritory.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    var errorMessage by remember { mutableStateOf("") }

    // Carregar os dados do território
    LaunchedEffect(territorioId) {
        coroutineScope.launch {
            if (territorioId != null) {
                territorio = viewModel.buscarTerritorioPorId(territorioId)
                if (territorio != null) {
                    nome = territorio?.nome ?: ""
                    descricao = territorio?.descricao ?: ""
                } else {
                    errorMessage = "Território não encontrado."
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Editar Território",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Exibe a mensagem de erro, se houver
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Campos para editar o nome e a descrição do território
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Botão de salvar
        Button(
            onClick = {
                if (nome.isEmpty() || descricao.isEmpty()) {
                    errorMessage = "Todos os campos são obrigatórios!"
                } else {
                    coroutineScope.launch {
                        val territorioEditado = Territorio(
                            id = territorioId,
                            nome = nome,
                            descricao = descricao
                        )
                        viewModel.gravarTerritorio(territorioEditado)
                        navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Salvar", fontSize = 25.sp, color = Color.White)
        }
    }
}
