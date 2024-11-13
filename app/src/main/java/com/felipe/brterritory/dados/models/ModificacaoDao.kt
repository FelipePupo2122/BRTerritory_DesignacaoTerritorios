package com.felipe.brterritory.dados.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ModificacaoDao {
    @Query("SELECT * FROM modificacoes WHERE origem = 'Local'")
    fun listarLocais(): Flow<List<Modificacao>>

    @Query("SELECT * FROM modificacoes WHERE origem = 'Remoto'")
    fun listarRemotas(): Flow<List<Modificacao>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun gravar(modificacao: Modificacao)
}
