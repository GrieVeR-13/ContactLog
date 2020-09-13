package net.grieverc.contactlog.features.repo.room

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.repo.room.union.WorkerWithSpecialtysUnion

class ContactLogRepository(
    private val contactLogDatabase: ContactLogDatabase,
    private val globalDao: GlobalDao,
    private val applicationScope: CoroutineScope
) : Repository {

    override fun loadSpecialty() =
        globalDao.loadSpecialty().map {
            it.map { specialtyEntity ->
                specialtyEntity.toModel()
            }
        }

    override fun loadWorkerListBySpecialtyId(id: String) =
        globalDao.loadWorkerListBySpecialtyId(id).map {
            it?.toModel() ?: emptyList()
        }

    override fun loadWorkerById(id: String) =
        globalDao.loadWorkerById(id).map {
            it?.toModel()
        }

    override suspend fun insertWorkerList(workerList: List<WorkerModel>) {
        withContext(applicationScope.coroutineContext) {
            val workerWithSpecialtysUnionList =
                workerList.map { WorkerWithSpecialtysUnion(it) }
            globalDao.insert(workerWithSpecialtysUnionList)
        }
    }

    override suspend fun clearAll() {
        withContext(applicationScope.coroutineContext) {
            contactLogDatabase.clearAllTables()
        }
    }

}