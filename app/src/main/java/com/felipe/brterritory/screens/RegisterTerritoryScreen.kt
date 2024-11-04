// RegisterTerritoryScreen.kt
package com.felipe.brterritory.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileOutputStream

@Composable
fun RegisterTerritoryScreen() {
    var territoryId by remember { mutableStateOf(TextFieldValue()) }
    var fileUri by remember { mutableStateOf<Uri?>(null) }
    var territoriesList by remember { mutableStateOf(listOf<Pair<String, File>>()) }
    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                fileUri = uri
                Toast.makeText(context, "Arquivo selecionado: ${uri.path}", Toast.LENGTH_SHORT).show()
                //realizado com URI e biblioteca para selecionar os arquivos no repositorio local do Cel
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = territoryId,
            onValueChange = { territoryId = it },
            label = { Text("ID do Território") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { filePickerLauncher.launch("application/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar Imagem/PDF")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (territoryId.text.isNotEmpty() && fileUri != null) {
                    val file = fileUri!!.toFile(context)
                    territoriesList = territoriesList + (territoryId.text to file)
                    Toast.makeText(context, "Território salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    territoryId = TextFieldValue("")
                    fileUri = null
                } else {
                    Toast.makeText(context, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(territoriesList) { (id, file) ->
                Text(text = "ID: $id, Arquivo: ${file.name}", modifier = Modifier.padding(4.dp))
            }
        }
    }
}


private fun Uri.toFile(context: Context): File {
    val contentResolver = context.contentResolver
    val file = File(context.filesDir, this.lastPathSegment ?: "temp_file")
    val inputStream = contentResolver.openInputStream(this)

    inputStream?.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }

    return file
}

@Preview(showBackground = true)
@Composable
fun RegisterTerritoryScreenPreview() {
    RegisterTerritoryScreen()
}
