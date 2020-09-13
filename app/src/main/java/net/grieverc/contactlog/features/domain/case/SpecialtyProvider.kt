package net.grieverc.contactlog.features.domain.case

import net.grieverc.contactlog.core.interactor.UseCase
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.repo.room.Repository

class SpecialtyProvider(private val repository: Repository): UseCase() {
    fun getSpecialty() = repository.loadSpecialty()

    suspend fun save(workerList: List<WorkerModel>) =  repository.insertWorkerList(workerList)
    suspend fun clearAll() {
        repository.clearAll()
    }
}