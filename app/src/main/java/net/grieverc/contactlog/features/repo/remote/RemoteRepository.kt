package net.grieverc.contactlog.features.repo.remote

interface RemoteRepository {
    suspend fun importItems(url: String): ResponseRemoteEntity
}