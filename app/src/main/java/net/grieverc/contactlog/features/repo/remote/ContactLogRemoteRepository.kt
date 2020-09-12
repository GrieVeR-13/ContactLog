package net.grieverc.contactlog.features.repo.remote

import net.grieverc.contactlog.features.domain.model.WorkerModel

class ContactLogRemoteRepository(
    private val contactLogRemoteService: ContactLogRemoteService
) : RemoteRepository {


    override suspend fun importWorkerList(url: String): List<WorkerModel> {
        return contactLogRemoteService.load(url).toModelList()
    }

}