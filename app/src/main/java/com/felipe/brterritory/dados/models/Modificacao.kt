package com.felipe.brterritory.dados.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modificacoes")
data class Modificacao(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val territorioId: Int,
    val descricao: String,
    val tipo: String,
    val origem: String,
    val dataHora: Long
)
