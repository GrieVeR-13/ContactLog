package net.grieverc.contactlog.features.domain.case

import net.grieverc.contactlog.core.interactor.UseCase
import net.grieverc.contactlog.features.repo.room.Repository
import net.grieverc.contactlog.features.repo.room.union.SpecialtyWithWorkersUnion

class SpecialtyProvider(private val repository: Repository): UseCase() {
    fun getSpecialty() = repository.loadSpecialty()

    suspend fun save(specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>) =  repository.insert(specialtyWithWorkersUnionList)
    suspend fun clearAll() {
        repository.clearAll()
    }
}