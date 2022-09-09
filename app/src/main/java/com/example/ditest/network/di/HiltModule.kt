package com.example.ditest.network.di

import com.example.ditest.domain.MainRepository
import com.example.ditest.network.GetInfoFromApi
import com.example.ditest.network.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Singleton
    @Provides
    fun retrofitProvider() =
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    @Singleton
    @Provides
    fun getInterface(retrofit: Retrofit):GetInfoFromApi {
        return retrofit.create(GetInfoFromApi::class.java)
    }

    @Singleton
    @Provides
    fun getRepositoryImpl(getInfoFromApi: GetInfoFromApi):MainRepository{
        return MainRepositoryImpl(getInfoFromApi)
    }

}