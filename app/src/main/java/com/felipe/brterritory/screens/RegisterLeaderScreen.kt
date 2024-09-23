// RegisterLeaderScreen.kt
package com.felipe.brterritory.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegisterLeaderScreen() {
    var leaderName by remember { mutableStateOf("") }
    var leadersList by remember { mutableStateOf(listOf<String>()) }
    val context = LocalContext.current

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
                leadersList = leadersList + leaderName
                Toast.makeText(context, "Dirigente salvo com sucesso!", Toast.LENGTH_SHORT).show()
                leaderName = ""
            } else {
                Toast.makeText(context, "Preencha o nome do dirigente.", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(leadersList) { leader ->
                Text(text = leader, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterLeaderScreenPreview() {
    RegisterLeaderScreen()
}
