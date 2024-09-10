// RegisterLeaderScreen.kt
package com.felipe.brterritory.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipe.brterritory.database.Leader
import com.felipe.brterritory.database.TerritoryDatabase
import kotlinx.coroutines.launch

@Composable
fun RegisterLeaderScreen() {
    var leaderName by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db =  TerritoryDatabase.getInstance(context)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = leaderName,
            onValueChange = { leaderName = it },
            label = { Text("Nome do Dirigente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (leaderName.isNotEmpty()) {
                scope.launch {
                    db.leaderDao().insert(Leader(name = leaderName))
                    Toast.makeText(context, "Dirigente salvo com sucesso!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Preencha o nome do dirigente.", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar")
        }
    }
}

