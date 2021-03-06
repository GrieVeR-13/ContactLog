package net.grieverc.contactlog.features.domain.case

import android.util.Log
import net.grieverc.contactlog.core.interactor.UseCase
import net.grieverc.contactlog.features.repo.remote.RemoteRepository
import net.grieverc.contactlog.features.repo.room.Repository

class ContactLogImporter(private val remoteRepository: RemoteRepository,
                         private val repository: Repository) : UseCase() {

    suspend fun importToRepository(url: String) {
        try {
            val workerList = remoteRepository.importWorkerList(url)
            repository.insertWorkerList(workerList)

        } catch (ex: Exception) {
            Log.e(TAG, "Exception: Import Remote Data", ex)
        }
    }

    companion object {
        private const val TAG = "ContactLogImporter"
    }
}