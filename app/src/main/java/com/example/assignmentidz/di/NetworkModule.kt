package com.example.assignmentidz.di

import com.example.assignmentidz.employee_api_service.EmployeeApiService
import com.example.assignmentidz.repository.EmployeeRepository
import com.example.assignmentidz.repository.EmployeeRepositoryImpl
import com.example.assignmentidz.utils.Utils.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Retrofit2
    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    // EmployeeApiService
    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): EmployeeApiService {
        return retrofit.create(EmployeeApiService::class.java)
    }

    // Repository
    @Provides
    @Singleton
    fun providesRepositoryInstance(employeeApiService: EmployeeApiService): EmployeeRepository {
        return EmployeeRepositoryImpl(employeeApiService = employeeApiService)
    }
}