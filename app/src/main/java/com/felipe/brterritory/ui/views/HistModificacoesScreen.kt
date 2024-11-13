package com.felipe.brterritory.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.felipe.brterritory.dados.models.Modificacao
import com.felipe.brterritory.ui.viewmodels.ModificacoesViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistModificacoesScreen(viewModel: ModificacoesViewModel) {
    val modificacoesLocais = viewModel.modificacoesLocais.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                "Histórico de Modificações",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Text(
                        "Modificações Realizadas",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    modificacoesLocais.value.forEach { modificacao ->
                        ModificacaoItem(modificacao)
                    }
                }
            }
        }
    }
}

@Composable
fun ModificacaoItem(modificacao: Modificacao) {
    val formattedDate = formatDate(modificacao.dataHora)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ID: ${modificacao.territorioId}", style = MaterialTheme.typography.bodyMedium)
            Text("Descrição: ${modificacao.descricao}", style = MaterialTheme.typography.bodyMedium)
            Text("Tipo: ${modificacao.tipo}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text("Data: $formattedDate", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.height(16.dp))
            Text("Origem: ${modificacao.origem}", style = MaterialTheme.typography.bodySmall)
        }
    }
}


fun formatDate(timestamp: Long): String {
    return try {
        val date = Date(timestamp)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Formato desejado
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        "Data inválida"
    }
}
