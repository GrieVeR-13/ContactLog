package net.grieverc.contactlog.core.case

import android.util.Log
import net.grieverc.contactlog.repo.Repository
import net.grieverc.contactlog.service.UseCase

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