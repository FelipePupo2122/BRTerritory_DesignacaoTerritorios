package com.felipe.brterritory.screens.util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.felipe.brterritory.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BRTopBar(
    currentPage: String,
    onPageSelected: (String) -> Unit
) {
    val dropdownExpanded = remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentPage,
                fontSize = dimensionResource(id = R.dimen.app_bar_text_size).value.sp,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = { dropdownExpanded.value = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menu",
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    tint = Color.White
                )
            }
        },
        actions = {
            DropdownMenu(
                expanded = dropdownExpanded.value,
                onDismissRequest = { dropdownExpanded.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Registrar Territorio") },
                    onClick = {
                        onPageSelected("Register Territory")
                        dropdownExpanded.value = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Designar Territorio") },
                    onClick = {
                        onPageSelected("Rent Territory")
                        dropdownExpanded.value = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Territorios Designados") },
                    onClick = {
                        onPageSelected("View Rented Territories")
                        dropdownExpanded.value = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Registrar Dirigentes") },
                    onClick = {
                        onPageSelected("Register Leader")
                        dropdownExpanded.value = false
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.primaryColor)
        )
    )
}
