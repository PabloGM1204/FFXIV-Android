package com.example.ffxivproject.data.api.armour

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface ArmourApi {
    @GET("armoires")
    suspend fun getAllArmour(@Query("limit") limit: Int=20, @Query("offset") offset: Int=0): ArmourListResponse
    @GET("armoires/{id}/")
    suspend fun getDetailArmour(@Path("id") id: Int): ArmourDetailResponse
}

@Singleton
class ArmourService @Inject constructor(){
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ffxivcollect.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiArmour: ArmourApi = retrofit.create(ArmourApi::class.java)
}