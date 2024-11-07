package com.felipe.brterritory.dados.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.dados.models.TerritorioDao

// Banco de dados para Territórios
@Database(entities = [Territorio::class], version = 1)
abstract class TerritorioDB : RoomDatabase() {
    abstract fun getTerritorioDao(): TerritorioDao
}

// Função para abrir o banco de dados
fun abrirBanco(context: Context): TerritorioDB {
    return Room.databaseBuilder(
        context.applicationContext,
        TerritorioDB::class.java,
        name = "territorios.db"
    ).build()
}
