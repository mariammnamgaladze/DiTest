package com.example.ditest.network

import com.example.ditest.model.Courses
import retrofit2.Response
import retrofit2.http.GET

interface GetInfoFromApi {

    @GET("v3/4167a598-b68c-420f-b6e1-fef68b89a10d")
    suspend fun getInfoFromApi(): Response<Courses>

}