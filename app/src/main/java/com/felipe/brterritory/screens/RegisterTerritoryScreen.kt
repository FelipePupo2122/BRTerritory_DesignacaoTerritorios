package com.felipe.brterritory.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import com.felipe.brterritory.database.Territory
import com.felipe.brterritory.database.TerritoryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

@Composable
fun RegisterTerritoryScreen() {
    var territoryId by remember { mutableStateOf(TextFieldValue()) }
    var fileUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    // Launcher para selecionar o arquivo
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                fileUri = uri
                Toast.makeText(context, "Arquivo selecionado: ${uri.path}", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val db = TerritoryDatabase.getInstance(context)
    val scope = rememberCoroutineScope()

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
                    scope.launch {
                        // Chama a função de salvar com ID como String
                        saveTerritoryToLocalDatabase(db, territoryId.text, fileUri!!, context)
                    }
                } else {
                    Toast.makeText(context, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
    }
}

private suspend fun saveTerritoryToLocalDatabase(
    db: TerritoryDatabase,
    territoryId: String,
    uri: Uri,
    context: Context
) {
    val file = uri.toFile(context)
    val territory = Territory(territoryId = territoryId, filePath = file.path)

    withContext(Dispatchers.IO) {
        db.territoryDao().insert(territory)
    }
    Toast.makeText(context, "Território salvo com sucesso!", Toast.LENGTH_SHORT).show()
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
