package com.felipe.brterritory.dados.repositories

import com.felipe.brterritory.dados.models.Territorio
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun listar(): Flow<List<Territorio>>
    suspend fun buscarPorId(idx: Int): Territorio?
    suspend fun gravar(territorio: Territorio)
    suspend fun excluir(territorio: Territorio)

}
