package com.example.assignmentidz.repository

import com.example.assignmentidz.employee_api_service.EmployeeApiService
import com.example.assignmentidz.model_class.ApiResult
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val employeeApiService: EmployeeApiService
): EmployeeRepository {

    override suspend fun getAllEmployees(): ApiResult {
        return employeeApiService.getAllEmployees()
    }

}