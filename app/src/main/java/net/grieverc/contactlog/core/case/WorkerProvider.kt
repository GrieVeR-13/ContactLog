package net.grieverc.contactlog.core.case

import net.grieverc.contactlog.repo.Repository
import net.grieverc.contactlog.service.UseCase

class WorkerProvider(private val repository: Repository) : UseCase() {
    fun getWorkerList(specialtyId: String) = repository.loadWorkerListBySpecialtyId(specialtyId)
    fun getWorker(id: String) = repository.loadWorkerById(id)
}