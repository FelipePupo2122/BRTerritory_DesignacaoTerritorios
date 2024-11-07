package com.felipe.brterritory.dados.repositories

import com.felipe.brterritory.dados.models.Territorio
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

// Repositório remoto para manipulação de Territorios no Firebase
class RemoteRepository : IRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val territorioCollection = firestore.collection("territorios")

    override fun listar(): Flow<List<Territorio>> = callbackFlow {
        val listener = territorioCollection.addSnapshotListener { dados, erros ->
            if (erros != null) {
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null) {
                val territorios = dados.documents.mapNotNull {
                    it.toObject(Territorio::class.java)
                }
                trySend(territorios).isSuccess
            }
        }
        awaitClose { listener.remove() }
    }

    override suspend fun buscarPorId(idx: Int): Territorio? {
        val dados = territorioCollection.document(idx.toString()).get().await()
        return dados.toObject(Territorio::class.java)
    }

    private suspend fun getId(): Int {
        val dados = territorioCollection.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravar(territorio: Territorio) {
        val document: DocumentReference
        if (territorio.id == null) {
            territorio.id = getId()
            document = territorioCollection.document(territorio.id.toString())
        } else {
            document = territorioCollection.document(territorio.id.toString())
        }
        document.set(territorio).await()
    }

    override suspend fun excluir(territorio: Territorio) {
        territorioCollection.document(territorio.id.toString()).delete().await()
    }
}
