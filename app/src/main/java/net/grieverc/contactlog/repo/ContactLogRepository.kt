package net.grieverc.contactlog.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.grieverc.contactlog.repo.remote.ContactLogRemoteData
import net.grieverc.contactlog.repo.room.ContactLogDatabase
import net.grieverc.contactlog.repo.room.GlobalDao
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity
import net.grieverc.contactlog.repo.room.union.SpecialtyWithWorkersUnion

object SampleData {
    val specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>

    init {
        val spec1 = SpecialtyEntity(
            name = "Инженер",
            desciption = ""
        )
        val spec2 = SpecialtyEntity(
            name = "Программист",
            desciption = ""
        )
        val spec3 = SpecialtyEntity(
            name = "Бухгалтер",
            desciption = ""
        )
        specialtyWithWorkersUnionList = listOf(
            SpecialtyWithWorkersUnion(
                specialty = spec1,
                workerList = listOf(
                    WorkerEntity(
                        first_name = "Иван",
                        surname = "Иванов",
                        age = 21,
                        specialtyFId = spec1.specialtyId
                    ),
                    WorkerEntity(
                        first_name = "Петр",
                        surname = "Иванов",
                        age = 22,
                        specialtyFId = spec1.specialtyId
                    )
                )
            ),
            SpecialtyWithWorkersUnion(
                specialty = spec2,
                workerList = listOf(
                    WorkerEntity(
                        first_name = "Петр",
                        surname = "Петров",
                        age = 21,
                        specialtyFId = spec2.specialtyId
                    ),
                    WorkerEntity(
                        first_name = "Иван",
                        surname = "Петров",
                        age = 22,
                        specialtyFId = spec2.specialtyId
                    )
                )
            ),
            SpecialtyWithWorkersUnion(
                specialty = spec3,
                workerList = emptyList()
            )
        )
    }
}

class ContactLogRepository(
    val contactLogDatabase: ContactLogDatabase,
    val contactLogRemoteData: ContactLogRemoteData,
    val globalDao: GlobalDao
) {

    fun loadSpecialty() =
        globalDao.loadSpecialty().map {
            it.map { specialtyEntity ->
                specialtyEntity.toModel()
            }
        }

    fun loadWorkerListBySpecialtyId(id: String) =
        globalDao.loadWorkerListBySpecialtyId(id).map {
            it?.toModel() ?: emptyList()
        }


    fun loadWorkerById(id: String) =
        globalDao.loadWorkerById(id).map {
            it?.toModel()
        }

    suspend fun insert(specialty: SpecialtyEntity) {
        withContext(Dispatchers.Default) {
            globalDao.insert(specialty)
        }
    }

    suspend fun insert(specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>) {
        withContext(Dispatchers.Default) {
            globalDao.insert(specialtyWithWorkersUnionList)
        }
    }

    suspend fun clearAll() {
        withContext(Dispatchers.Default) {
            contactLogDatabase.clearAllTables()
        }
    }

    suspend fun importItems(url: String) {
        val responseRemoteItem = contactLogRemoteData.load(url)
        val workerList = responseRemoteItem.toModelList()
        val specialtyWithWorkersUnionList = SpecialtyWithWorkersUnion.fromModelList(workerList)
        insert(specialtyWithWorkersUnionList)
    }
}