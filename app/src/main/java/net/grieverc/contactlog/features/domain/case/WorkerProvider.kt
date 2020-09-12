package net.grieverc.contactlog.features.domain.case

import net.grieverc.contactlog.core.interactor.UseCase
import net.grieverc.contactlog.features.repo.room.Repository

class WorkerProvider(private val repository: Repository) : UseCase() {
    fun getWorkerList(specialtyId: String) = repository.loadWorkerListBySpecialtyId(specialtyId)
    fun getWorker(id: String) = repository.loadWorkerById(id)
}