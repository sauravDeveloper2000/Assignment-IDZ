package com.example.assignmentidz.model_class

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult(
    val employees: List<Employee>
)