package net.grieverc.contactlog.features.repo.remote

import net.grieverc.contactlog.features.domain.model.WorkerModel

interface RemoteRepository {
    suspend fun importWorkerList(url: String): List<WorkerModel>
}