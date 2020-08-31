package net.grieverc.contactlog.core.case

import net.grieverc.contactlog.repo.Repository
import net.grieverc.contactlog.repo.room.union.SpecialtyWithWorkersUnion
import net.grieverc.contactlog.service.UseCase

class SpecialtyProvider(private val repository: Repository): UseCase() {
    fun getSpecialty() = repository.loadSpecialty()

    suspend fun save(specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>) =  repository.insert(specialtyWithWorkersUnionList)
    suspend fun clearAll() {
        repository.clearAll()
    }
}