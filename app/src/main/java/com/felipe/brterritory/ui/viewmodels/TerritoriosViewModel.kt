package com.felipe.brterritory.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipe.brterritory.dados.models.Territorio
import com.felipe.brterritory.dados.repositories.IRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TerritoriosViewModel(
    private val repository: IRepository
) : ViewModel() {

    private val _territorios = MutableStateFlow<List<Territorio>>(emptyList())
    val territorios: StateFlow<List<Territorio>> get() = _territorios

    init {
        viewModelScope.launch {
            repository.listar().collectLatest { lista ->
                _territorios.value = lista
            }
        }
    }

    suspend fun buscarTerritorioPorId(territorioId: Int): Territorio? {
        return repository.buscarPorId(territorioId)
    }

    fun gravarTerritorio(territorio: Territorio) {
        viewModelScope.launch {
            repository.gravar(territorio)
        }
    }

    fun excluirTerritorio(territorioId: Int) {
        viewModelScope.launch {
            val territorio = repository.buscarPorId(territorioId)
            if (territorio != null) {
                repository.excluir(territorio)
            }
        }
    }

    // Função para filtrar territórios por dirigente
    fun buscarTerritoriosPorDirigente(dirigente: String): List<Territorio> {
        return _territorios.value.filter {
            it.dirigente.contains(dirigente, ignoreCase = true)
        }
    }
}

