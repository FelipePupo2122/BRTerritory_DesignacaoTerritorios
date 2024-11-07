package com.felipe.brterritory.dados.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TerritorioDao {

    @Query("SELECT * FROM tab_territorio")
    fun listar(): Flow<List<Territorio>>

    @Query("SELECT * FROM tab_territorio WHERE id = :territorioId")
    suspend fun buscarPorId(territorioId: Int): Territorio?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun gravar(territorio: Territorio)

    @Delete
    suspend fun excluir(territorio: Territorio)
}
