package net.grieverc.contactlog.features.repo.remote

import net.grieverc.contactlog.features.repo.remote.ResponseRemoteEntity
import retrofit2.Call
import retrofit2.http.GET

interface RemoteApi {
    @GET("65gb/static/raw/master/testTask.json")
    fun getData(): Call<ResponseRemoteEntity>
}