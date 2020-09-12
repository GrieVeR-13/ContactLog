package net.grieverc.contactlog.repo.remote

import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import java.io.IOException

interface RemoteApi {
    @GET("65gb/static/raw/master/testTask.json")
    fun getData(): Call<ResponseRemoteItem>
}

class ContactLogRemoteData(retrofit: Retrofit) {
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