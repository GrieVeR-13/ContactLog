package net.grieverc.contactlog.features.domain.case

import android.util.Log
import net.grieverc.contactlog.core.interactor.UseCase
import net.grieverc.contactlog.features.repo.room.Repository

class ContactLogImporter(private val repository: Repository) : UseCase() {
    private val TAG = "SpecialtyImporter"

    suspend fun import(url: String) {
        try {
            repository.importItems(url)
        } catch (ex: Exception) {
            Log.e(TAG, "Exception: Import Remote Data", ex)
        }
    }

}