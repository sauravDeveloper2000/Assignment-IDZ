package com.example.assignmentidz.employee_api_service

import com.example.assignmentidz.model_class.ApiResult
import com.example.assignmentidz.utils.Utils.SPECIFIC_URL
import retrofit2.http.GET

interface EmployeeApiService {
    @GET(SPECIFIC_URL)
    suspend fun getAllEmployees(): ApiResult
}