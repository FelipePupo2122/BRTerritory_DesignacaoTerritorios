package com.felipe.brterritory.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RentTerritoryScreen() {
    var selectedTerritory by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var leader by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = selectedTerritory,
            onValueChange = { selectedTerritory = it },
            label = { Text("Território Selecionado") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        DatePicker(selectedDate) { date ->
            selectedDate = date
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = leader,
            onValueChange = { leader = it },
            label = { Text("Dirigente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Número de Telefone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Alugar")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Column {
        Text(text = "Data de Aluguel: ${selectedDate.format(dateFormatter)}")

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { openDialog = true }) {
            Text("Selecionar Data")
        }

        if (openDialog) {
            DatePickerDialog(
                initialDate = selectedDate,
                onDateSelected = { date ->
                    onDateSelected(date)
                    openDialog = false
                },
                onDismissRequest = { openDialog = false }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerDialog(
    initialDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity
        ?:
        return

    val initialMillis = initialDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()

    val datePicker = remember {
        MaterialDatePicker.Builder.datePicker()
            .setSelection(initialMillis)
            .build()
    }

    LaunchedEffect(datePicker) {
        datePicker.addOnPositiveButtonClickListener { selection ->
            val date = LocalDate.ofEpochDay(selection / (24 * 60 * 60 * 1000))
            onDateSelected(date)
        }
        datePicker.addOnDismissListener {
            onDismissRequest()
        }
        datePicker.show(activity.supportFragmentManager, "DatePicker")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun RentTerritoryScreenPreview() {
    RentTerritoryScreen()
}
