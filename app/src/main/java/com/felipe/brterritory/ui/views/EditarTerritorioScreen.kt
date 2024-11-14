import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarTerritorioScreen(
    viewModel: TerritoriosViewModel,
    territorioId: Int?,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var dirigente by remember { mutableStateOf("") }
    var diaDesignado by remember { mutableStateOf("") }
    var dataTerritorioDevolvido by remember { mutableStateOf("") } // Novo estado para o campo de devolução

    var territorio: Territorio? by remember { mutableStateOf(null) }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // DatePicker para o campo "Data Territorio Devolvido"
    val datePickerDialogDevolvido = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            dataTerritorioDevolvido = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    LaunchedEffect(territorioId) {
        coroutineScope.launch {
            if (territorioId != null) {
                territorio = viewModel.buscarTerritorioPorId(territorioId)
                if (territorio != null) {
                    nome = territorio?.nome ?: ""
                    descricao = territorio?.descricao ?: ""
                    dirigente = territorio?.dirigente ?: ""
                    diaDesignado = territorio?.diaDesignado ?: ""
                    dataTerritorioDevolvido = territorio?.dataTerritorioDevolvido ?: "" // Carrega o valor salvo
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

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

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

        OutlinedTextField(
            value = dirigente,
            onValueChange = { dirigente = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Dirigente") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = diaDesignado,
            onValueChange = { diaDesignado = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Data Designada") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dataTerritorioDevolvido,
            onValueChange = { dataTerritorioDevolvido = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Data Território Devolvido") },
            readOnly = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { datePickerDialogDevolvido.show() },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Selecionar Data de Devolução", fontSize = 16.sp)
        }

        Button(
            onClick = {
                if (nome.isEmpty() || descricao.isEmpty() || dirigente.isEmpty() || diaDesignado.isEmpty()) {
                    errorMessage = "Todos os campos são obrigatórios!"
                } else {
                    coroutineScope.launch {
                        val territorioEditado = Territorio(
                            id = territorioId,
                            nome = nome,
                            descricao = descricao,
                            dirigente = dirigente,
                            diaDesignado = diaDesignado,
                            dataTerritorioDevolvido = dataTerritorioDevolvido // Salva o novo campo
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
