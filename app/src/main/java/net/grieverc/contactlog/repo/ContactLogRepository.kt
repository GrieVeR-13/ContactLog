package net.grieverc.contactlog.repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.grieverc.contactlog.core.SpecialtyModel
import net.grieverc.contactlog.core.WorkerModel
import net.grieverc.contactlog.repo.remote.ContactLogRemoteData
import net.grieverc.contactlog.repo.room.ContactLogDatabase
import net.grieverc.contactlog.repo.room.GlobalDao
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity
import net.grieverc.contactlog.repo.room.union.SpecialtyWithWorkersUnion
import org.threeten.bp.LocalDate

interface Repository {
    fun loadSpecialty(): Flow<List<SpecialtyModel>>
    suspend fun insert(specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>)

    fun loadWorkerListBySpecialtyId(id: String): Flow<List<WorkerModel>>
    fun loadWorkerById(id: String): Flow<WorkerModel?>

    suspend fun importItems(url: String)
    suspend fun clearAll()
}

class ContactLogRepository(
    private val contactLogDatabase: ContactLogDatabase,
    private val contactLogRemoteData: ContactLogRemoteData,
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


    override suspend fun insert(specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>) {
        withContext(applicationScope.coroutineContext) {
            globalDao.insert(specialtyWithWorkersUnionList)
        }
    }

    override suspend fun clearAll() {
        withContext(applicationScope.coroutineContext) {
            contactLogDatabase.clearAllTables()
        }
    }

    override suspend fun importItems(url: String) {
        val responseRemoteItem = contactLogRemoteData.load(url)
        val workerList = responseRemoteItem.toModelList()
        val specialtyWithWorkersUnionList = SpecialtyWithWorkersUnion.fromModelList(workerList)
        insert(specialtyWithWorkersUnionList)
    }

}