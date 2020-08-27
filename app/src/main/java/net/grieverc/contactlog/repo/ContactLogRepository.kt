package net.grieverc.contactlog.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.grieverc.contactlog.repo.specialty.SpecialtyEntity
import net.grieverc.contactlog.repo.specialty.SpecialtyModel

object SampleData {
    val specialyList = mutableListOf(
        SpecialtyModel(
            name = "n1",
            description = "d1"
        ),
        SpecialtyModel(
            name = "n2",
            description = "d2"
        )
    )
}

class ContactLogRepository(val contactLogDatabase: ContactLogDatabase, val specialtyEntityStore: SpecialtyEntity.Store) {

    fun specialtyLoad() =
        specialtyEntityStore.loadAll().map {
            it.map { specialtyEntity ->
                specialtyEntity.toModel()
            }
        }

    suspend fun insert(specialtyEntity: SpecialtyEntity) {
        withContext(Dispatchers.Default) {
            specialtyEntityStore.insert(specialtyEntity)
        }
    }

    suspend fun clearAll() {
        withContext(Dispatchers.Default) {
            contactLogDatabase.clearAllTables()
        }
    }
}