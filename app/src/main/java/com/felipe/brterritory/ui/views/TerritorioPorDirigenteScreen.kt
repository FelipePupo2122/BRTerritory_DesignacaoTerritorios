package com.felipe.brterritory.ui.views

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel

@Composable
fun TerritorioPorDirigenteScreen(
    viewModel: TerritoriosViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    var dirigenteQuery by remember { mutableStateOf(TextFieldValue("")) }
    var diaDesignadoQuery by remember { mutableStateOf(TextFieldValue("")) }  // Campo para o dia designado
    val territorios by viewModel.territorios.collectAsState()
    var isConnected by remember { mutableStateOf(true) }

    // Função para verificar a conexão
    fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    // Monitorar a conexão
    LaunchedEffect(Unit) {
        isConnected = isInternetAvailable()
        snapshotFlow { isInternetAvailable() }
            .collect { newStatus -> isConnected = newStatus }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(
                text = "Territórios por Dirigente",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de busca por dirigente
            TextField(
                value = dirigenteQuery,
                onValueChange = { dirigenteQuery = it },
                label = { Text("Buscar por Dirigente") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de busca por dia designado
            TextField(
                value = diaDesignadoQuery,
                onValueChange = { diaDesignadoQuery = it },
                label = { Text("Buscar por Dia Designado (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Atualiza a conexão
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isConnected) "Conectado ao Firebase" else "Desconectado do Firebase",
                    color = if (isConnected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    fontSize = 16.sp
                )
            }
        }

        // Lista rolável
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Aplicação do filtro por dirigente e dia designado
            val filteredTerritorios = territorios.filter { territorio ->
                (dirigenteQuery.text.isBlank() || territorio.dirigente.contains(dirigenteQuery.text, ignoreCase = true)) &&
                        (diaDesignadoQuery.text.isBlank() || territorio.diaDesignado == diaDesignadoQuery.text)
            }

            if (filteredTerritorios.isEmpty() && (dirigenteQuery.text.isNotEmpty() || diaDesignadoQuery.text.isNotEmpty())) {
                item {
                    Text(
                        text = "Nenhum território encontrado para os critérios de pesquisa.",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else if (filteredTerritorios.isEmpty()) {
                item {
                    Text(
                        text = "Use os campos acima para pesquisar por territórios.",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                items(filteredTerritorios) { territorio ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                    ) {
                        Text(
                            text = territorio.nome,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Descrição: ${territorio.descricao}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Dirigente: ${territorio.dirigente}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Dia Designado: ${territorio.diaDesignado}",
                            fontSize = 16.sp
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("editarTerritorio/${territorio.id}")
                                }
                            ) {
                                Text(text = "Editar")
                            }
                            Button(
                                onClick = {
                                    navController.navigate("excluirTerritorio/${territorio.id}")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.error
                                )
                            ) {
                                Text(text = "Excluir")
                            }
                        }
                    }
                }
            }
        }
    }
}

