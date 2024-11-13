package com.felipe.brterritory.dados.repositories

import android.util.Log
import com.felipe.brterritory.dados.models.Modificacao
import com.felipe.brterritory.dados.models.ModificacaoDao
import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.dados.models.TerritorioDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class LocalRepository(
    private val dao: TerritorioDao,
    private val modificacaoDao: ModificacaoDao
) : IRepository {

    override fun listar(): Flow<List<Territorio>> {
        return dao.listar()
    }

    override suspend fun buscarPorId(idx: Int): Territorio? {
        return dao.buscarPorId(idx)
    }

    override suspend fun gravar(territorio: Territorio) {
        dao.gravar(territorio)
        territorio.id?.let { registrarModificacao(it, "Inclusão", "Local") }
    }

    override suspend fun excluir(territorio: Territorio) {
        dao.excluir(territorio)
        territorio.id?.let { registrarModificacao(it, "Exclusão", "Local") }
    }

    private suspend fun registrarModificacao(territorioId: Int, tipo: String, origem: String) {
        try {
            val modificacao = Modificacao(
                territorioId = territorioId,
                descricao = "Modificação de território $tipo",
                tipo = tipo,
                origem = origem,
                dataHora = Date().time
            )
            withContext(Dispatchers.IO) {
                modificacaoDao.gravar(modificacao) // Grava a modificação
            }
            Log.d("LocalRepository", "Modificação registrada com sucesso.")
        } catch (e: Exception) {
            Log.e("LocalRepository", "Erro ao registrar modificação: $e")
        }
    }
}
