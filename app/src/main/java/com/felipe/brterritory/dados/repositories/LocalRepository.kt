package com.felipe.brterritory.dados.repositories

import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.dados.models.TerritorioDao
import kotlinx.coroutines.flow.Flow

// Repositório local para manipulação de Territorios
class LocalRepository(
    private val dao: TerritorioDao
) : IRepository {

    override fun listar(): Flow<List<Territorio>> {
        return dao.listar()
    }

    override suspend fun buscarPorId(idx: Int): Territorio? {
        return dao.buscarPorId(idx)
    }

    override suspend fun gravar(territorio: Territorio) {
        dao.gravar(territorio)
    }

    override suspend fun excluir(territorio: Territorio) {
        dao.excluir(territorio)
    }
}
