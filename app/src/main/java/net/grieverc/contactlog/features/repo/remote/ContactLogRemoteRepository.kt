package net.grieverc.contactlog.features.repo.remote

class ContactLogRemoteRepository(
    private val contactLogRemoteService: ContactLogRemoteService
) : RemoteRepository {


    override suspend fun importItems(url: String): ResponseRemoteEntity {
        return contactLogRemoteService.load(url)
    }

}