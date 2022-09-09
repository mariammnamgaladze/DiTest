package com.example.ditest.domain

import com.example.ditest.model.Courses
import com.example.ditest.resources.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface MainRepository {

     suspend fun getResponseFromApi ( ) : Flow<ResponseHandler<Courses>>
}