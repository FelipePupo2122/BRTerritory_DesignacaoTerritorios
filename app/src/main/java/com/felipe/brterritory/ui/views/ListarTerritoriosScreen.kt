package com.felipe.brterritory.ui.views

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext

@Composable
fun ListarTerritoriosScreen(
    viewModel: TerritoriosViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val territorios by viewModel.territorios.collectAsState()
    var isConnected by remember { mutableStateOf(true) }

    // Função para verificar conexão
    fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

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
                text = "Bem-vindo ao BRTerritory",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Adicione os seus territórios",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("incluirTerritorio")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Adicionar Território", fontSize = 18.sp)
            }

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

                IconButton(
                    onClick = {
                        isConnected = isInternetAvailable()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Atualizar Conexão",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (territorios.isEmpty()) {
                item {
                    Text(
                        text = "Nenhum território cadastrado.",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                items(territorios) { territorio ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                            .then(Modifier.padding(horizontal = 8.dp))
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

