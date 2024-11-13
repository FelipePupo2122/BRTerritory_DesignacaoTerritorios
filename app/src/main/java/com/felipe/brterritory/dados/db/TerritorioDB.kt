package com.felipe.brterritory.dados.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.dados.models.TerritorioDao
import com.felipe.brterritory.dados.models.Modificacao // Importa Modificacao
import com.felipe.brterritory.dados.models.ModificacaoDao // Importa ModificacaoDao

@Database(entities = [Territorio::class, Modificacao::class], version = 3, exportSchema = false)
abstract class TerritorioDB : RoomDatabase() {
    abstract fun getTerritorioDao(): TerritorioDao
    abstract fun getModificacaoDao(): ModificacaoDao
}

fun abrirBanco(context: Context): TerritorioDB {
    return Room.databaseBuilder(
        context.applicationContext,
        TerritorioDB::class.java,
        name = "territorios.db"
    )
        .fallbackToDestructiveMigration()
        .build()
}
