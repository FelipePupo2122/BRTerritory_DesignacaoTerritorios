package com.felipe.brterritory.dados.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entidade Territorio para o banco de dados
@Entity(tableName = "tab_territorio")
data class Territorio(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nome: String,
    val descricao: String,
    val ativo: Boolean = true
) {
    constructor() : this(null, "", "", true)
}
