package net.grieverc.contactlog.features.repo.remote

import android.content.Context
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.grieverc.contactlog.R
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

    companion object  {
        fun retrofitNewInstance(context: Context): Retrofit {
            val moshi = Moshi.Builder().add(MoshiLocalDateAdapter()).build()
            return Retrofit.Builder()
                .baseUrl(context.getString(R.string.remote_data_url_default))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
    }

}
