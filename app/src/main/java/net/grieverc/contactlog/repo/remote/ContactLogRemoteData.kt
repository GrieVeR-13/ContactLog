package net.grieverc.contactlog.repo.remote

import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class ContactLogRemoteData(val okHttpClient: OkHttpClient) {
    private val moshi = Moshi.Builder().add(MoshiLocalDateAdapter()).build()
    val adapter = moshi.adapter(ResponseRemoteItem::class.java)

    suspend fun load(url: String) = withContext(Dispatchers.IO) {
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful) {
            val responceRemoteItem = response.body?.let {
                adapter.fromJson(it.source())
            }
                ?: throw IOException("Response body is null: $response")
            responceRemoteItem
        } else {
            throw IOException("Unexpected response code: ${response.code}")
        }
    }

}