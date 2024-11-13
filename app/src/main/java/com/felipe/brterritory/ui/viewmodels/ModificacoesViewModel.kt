package com.felipe.brterritory.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipe.brterritory.dados.models.Modificacao
import com.felipe.brterritory.dados.models.ModificacaoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ModificacoesViewModel(
    private val modificacaoDao: ModificacaoDao
) : ViewModel() {

    val modificacoesLocais: Flow<List<Modificacao>> = modificacaoDao.listarLocais()

    fun adicionarModificacao(modificacao: Modificacao) {
        viewModelScope.launch {
            modificacaoDao.gravar(modificacao)
        }
    }
}
