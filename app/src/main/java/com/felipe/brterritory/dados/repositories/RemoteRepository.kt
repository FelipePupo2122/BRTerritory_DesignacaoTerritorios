package com.felipe.brterritory.dados.repositories

import com.felipe.brterritory.dados.models.Territorio
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await


class RemoteRepository(
    private val localRepository: LocalRepository
) : IRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val territorioCollection = firestore.collection("territorios")

    override fun listar(): Flow<List<Territorio>> {
        return localRepository.listar()
    }

    override suspend fun buscarPorId(idx: Int): Territorio? {
        return localRepository.buscarPorId(idx) ?: fetchFromRemote(idx)
    }

    override suspend fun gravar(territorio: Territorio) {
        localRepository.gravar(territorio)
        syncToFirebase(territorio)
    }

    override suspend fun excluir(territorio: Territorio) {
        localRepository.excluir(territorio)
        deleteFromFirebase(territorio)
    }


    private suspend fun syncToFirebase(territorio: Territorio) {
        val document: DocumentReference
        if (territorio.id == null) {
            territorio.id = getNextId()
            document = territorioCollection.document(territorio.id.toString())
        } else {
            document = territorioCollection.document(territorio.id.toString())
        }
        document.set(territorio).await()
    }

    private suspend fun deleteFromFirebase(territorio: Territorio) {
        territorioCollection.document(territorio.id.toString()).delete().await()
    }

    private suspend fun getNextId(): Int {
        val dados = territorioCollection.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    private suspend fun fetchFromRemote(idx: Int): Territorio? {
        val dados = territorioCollection.document(idx.toString()).get().await()
        return dados.toObject(Territorio::class.java)
    }
}
