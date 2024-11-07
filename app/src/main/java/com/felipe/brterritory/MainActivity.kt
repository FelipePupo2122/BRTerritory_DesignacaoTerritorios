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
import com.felipe.brterritory.ui.viewmodels.TerritoriosViewModel
import com.felipe.brterritory.ui.views.TerritorioNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isLocal = false

            val repository: IRepository

            if (isLocal) {
                val db = remember { abrirBanco(this) }
                val dao = db.getTerritorioDao()
                repository = LocalRepository(dao)
            } else {
                // para o remoto é criado o local antes
                val db = remember { abrirBanco(this) }
                val dao = db.getTerritorioDao()
                val localRepository = LocalRepository(dao)

                // agora cria o remoto e dai passa o local
                repository = RemoteRepository(localRepository)
            }

            // view model ja vai iniciar com o offline first sempre buscando localmente
            val viewModel = TerritoriosViewModel(repository)
            TerritorioNavHost(viewModel)
        }
    }
}
