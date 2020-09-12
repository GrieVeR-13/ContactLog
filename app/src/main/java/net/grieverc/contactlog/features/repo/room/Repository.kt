package net.grieverc.contactlog.features.repo.room

import kotlinx.coroutines.flow.Flow
import net.grieverc.contactlog.features.domain.model.SpecialtyModel
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.repo.room.union.SpecialtyWithWorkersUnion

interface Repository {
    fun loadSpecialty(): Flow<List<SpecialtyModel>>
    suspend fun insert(specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>)

    fun loadWorkerListBySpecialtyId(id: String): Flow<List<WorkerModel>>
    fun loadWorkerById(id: String): Flow<WorkerModel?>

    suspend fun clearAll()
}