package com.felipe.brterritory.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Novo Território",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

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

        // Exibe mensagem de erro, se houver
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
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
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Salvar", fontSize = 25.sp)
        }
    }
}
