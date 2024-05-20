package com.example.assignmentidz.repository

import com.example.assignmentidz.employee_api_service.EmployeeApiService
import com.example.assignmentidz.model_class.ApiResult

interface EmployeeRepository {
    suspend fun getAllEmployees(): ApiResult
}