import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun IncluirTerritorioScreen(
    viewModel: TerritoriosViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var dirigente by remember { mutableStateOf("") }
    var diaDesignado by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            diaDesignado = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

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
            value = dirigente,
            onValueChange = { dirigente = it },
            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Dirigente") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = diaDesignado,
            onValueChange = { diaDesignado = it },
            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Data Designada") },
            readOnly = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Selecionar Data", fontSize = 16.sp)
        }

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
                if (nome.isEmpty() || descricao.isEmpty() || dirigente.isEmpty() || diaDesignado.isEmpty()) {
                    errorMessage = "Todos os campos são obrigatórios!"
                } else {
                    coroutineScope.launch {
                        val novoTerritorio = Territorio(
                            nome = nome,
                            descricao = descricao,
                            dirigente = dirigente,
                            diaDesignado = diaDesignado
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
