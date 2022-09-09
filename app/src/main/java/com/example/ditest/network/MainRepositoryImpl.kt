package com.example.ditest.network

import com.example.ditest.domain.MainRepository
import com.example.ditest.model.Courses
import com.example.ditest.resources.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val api: GetInfoFromApi) : MainRepository {


    override suspend fun getResponseFromApi(): Flow<ResponseHandler<Courses>> = flow {
        val response = api.getInfoFromApi()
        if (response.isSuccessful && response.body() != null) {
            emit(ResponseHandler.Success(response.body()!!))
        } else emit(ResponseHandler.Error("Error ", false))

    }
}