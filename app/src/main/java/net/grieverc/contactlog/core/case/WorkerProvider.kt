package net.grieverc.contactlog.core.case

import net.grieverc.contactlog.repo.Repository

class WorkerProvider(private val repository: Repository) : UseCase() {
    fun getWorkerList(specialtyId: String) = repository.loadWorkerListBySpecialtyId(specialtyId)
    fun getWorker(id: String) = repository.loadWorkerById(id)
}