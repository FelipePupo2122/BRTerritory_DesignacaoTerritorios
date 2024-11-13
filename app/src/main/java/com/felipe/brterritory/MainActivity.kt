package com.felipe.brterritory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.felipe.brterritory.dados.db.abrirBanco
import com.felipe.brterritory.dados.repositories.IRepository
import com.felipe.brterritory.dados.repositories.LocalRepository
import com.felipe.brterritory.dados.repositories.RemoteRepository
import com.felipe.brterritory.ui.viewmodels.ModificacoesViewModel
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel
import com.felipe.brterritory.ui.views.TerritorioNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val db = remember { abrirBanco(this) }
            val territorioDao = db.getTerritorioDao()
            val modificacaoDao = db.getModificacaoDao()

            val repository: IRepository = remember {
                val localRepository = LocalRepository(territorioDao, modificacaoDao)
                if (isLocal) {
                    localRepository
                } else {
                    RemoteRepository(localRepository)
                }
            }

            val territorioViewModel = remember { TerritoriosViewModel(repository) }
            val modificacaoViewModel = remember { ModificacoesViewModel(modificacaoDao) }

            TerritorioNavHost(territorioViewModel, modificacaoViewModel)
        }
    }

    companion object {
        private const val isLocal = false
    }
}
