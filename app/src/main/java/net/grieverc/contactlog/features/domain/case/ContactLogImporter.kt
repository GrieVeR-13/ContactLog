package net.grieverc.contactlog.features.domain.case

import android.util.Log
import net.grieverc.contactlog.R
import net.grieverc.contactlog.core.interactor.UseCase
import net.grieverc.contactlog.features.repo.remote.RemoteRepository
import net.grieverc.contactlog.features.repo.remote.ResponseRemoteEntity
import net.grieverc.contactlog.features.repo.room.Repository
import net.grieverc.contactlog.features.repo.room.union.SpecialtyWithWorkersUnion

class ContactLogImporter(private val remoteRepository: RemoteRepository,
                         private val repository: Repository) : UseCase() {

    suspend fun import(url: String) {
        try {
            val responseRemoteEntity = remoteRepository.importItems(url)
            val workerList = responseRemoteEntity.toModelList()
            val specialtyWithWorkersUnionList = SpecialtyWithWorkersUnion.fromModelList(workerList)
            repository.insert(specialtyWithWorkersUnionList)

        } catch (ex: Exception) {
            Log.e(TAG, "Exception: Import Remote Data", ex)
        }
    }

    companion object {
        private const val TAG = "ContactLogImporter"
    }
}