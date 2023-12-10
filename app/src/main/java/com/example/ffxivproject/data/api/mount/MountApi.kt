package com.example.ffxivproject.data.api.mount

import com.example.ffxivproject.data.api.armour.ArmourDetailResponse
import com.example.ffxivproject.data.api.armour.ArmourListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface MountApi {
    @GET("mounts")
    suspend fun getAllMounts(@Query("limit") limit: Int=20, @Query("offset") offset: Int=0): MountListResponse
    @GET("mounts/{id}/")
    suspend fun getDetailMounts(@Path("id") id: Int): MountDetailResponse
}

@Singleton
class MountService @Inject constructor(){
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ffxivcollect.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiMount: MountApi = retrofit.create(MountApi::class.java)
}