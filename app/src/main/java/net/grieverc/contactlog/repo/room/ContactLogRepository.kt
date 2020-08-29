package net.grieverc.contactlog.repo.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.grieverc.contactlog.repo.room.union.SpecialtyWithWorkerListUnion

object SampleData {
    val specialtyWithWorkerList: List<SpecialtyWithWorkerListUnion>

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
        specialtyWithWorkerList = listOf(
            SpecialtyWithWorkerListUnion(
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
            SpecialtyWithWorkerListUnion(
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
            SpecialtyWithWorkerListUnion(
                specialty = spec3,
                workerList = emptyList()
            )
        )
    }
}

class ContactLogRepository(
    val contactLogDatabase: ContactLogDatabase,
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

    suspend fun insert(specialtyWithWorkerList: SpecialtyWithWorkerListUnion) {
        withContext(Dispatchers.Default) {
            globalDao.insert(specialtyWithWorkerList)
        }
    }

    suspend fun clearAll() {
        withContext(Dispatchers.Default) {
            contactLogDatabase.clearAllTables()
        }
    }
}