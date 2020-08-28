package net.grieverc.contactlog.repo.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

object SampleData {
    val specialtyWithWorkerList: List<SpecialtyWithWorkerListEntity>

    init {
        val spec1 = SpecialtyEntity(
            name = "Инженер",
            desciption = "d1"
        )
        val spec2 = SpecialtyEntity(
            name = "Программист",
            desciption = "d2"
        )
        val spec3 = SpecialtyEntity(
            name = "Бухгалтер",
            desciption = "d3"
        )
        specialtyWithWorkerList = listOf(
            SpecialtyWithWorkerListEntity(
                specialty = spec1,
                workerList = listOf(
                    WorkerEntity(
                        first_name = "Иван1",
                        surname = "Иванов",
                        age = 21,
                        specialtyId = spec1.id
                    ),
                    WorkerEntity(
                        first_name = "Иван2",
                        surname = "Иванов",
                        age = 22,
                        specialtyId = spec1.id
                    )
                )
            ),
            SpecialtyWithWorkerListEntity(
                specialty = spec2,
                workerList = listOf(
                    WorkerEntity(
                        first_name = "Петр1",
                        surname = "Петров",
                        age = 21,
                        specialtyId = spec2.id
                    ),
                    WorkerEntity(
                        first_name = "Петр2",
                        surname = "Петров",
                        age = 22,
                        specialtyId = spec2.id
                    )
                )
            ),
            SpecialtyWithWorkerListEntity(
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

    fun loadSpecialtyWithWorkerListById(id: String) =
        globalDao.loadSpecialtyWithWorkerListById(id).map {
            it.toModel()
        }

    fun loadWorkerById(id: String) =
        globalDao.loadWorkerById(id).map {
            it.toModel()
        }

    suspend fun insert(specialty: SpecialtyEntity) {
        withContext(Dispatchers.Default) {
            globalDao.insert(specialty)
        }
    }

    suspend fun insert(specialtyWithWorkerList: SpecialtyWithWorkerListEntity) {
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