package net.grieverc.contactlog.features.repo.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.io.IOException


class ContactLogRemoteService(retrofit: Retrofit) {
    private val remoteApi = retrofit.create(RemoteApi::class.java)

    suspend fun load(url: String) = withContext(Dispatchers.IO) {
        val response = remoteApi.getData().execute()
        if (response.isSuccessful) {
            response.body()
                ?: throw IOException("Response body is null: $response")
        } else {
            throw IOException("Unexpected response code: ${response.code()}")
        }
    }

}
