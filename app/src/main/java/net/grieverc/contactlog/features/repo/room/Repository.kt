package net.grieverc.contactlog.features.repo.room

import kotlinx.coroutines.flow.Flow
import net.grieverc.contactlog.features.domain.model.SpecialtyModel
import net.grieverc.contactlog.features.domain.model.WorkerModel

interface Repository {
    fun loadSpecialty(): Flow<List<SpecialtyModel>>
    suspend fun insertWorkerList(workerList: List<WorkerModel>)

    fun loadWorkerListBySpecialtyId(id: String): Flow<List<WorkerModel>>
    fun loadWorkerById(id: String): Flow<WorkerModel?>

    suspend fun clearAll()
}