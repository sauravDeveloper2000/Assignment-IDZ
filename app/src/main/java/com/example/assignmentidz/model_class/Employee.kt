package com.example.assignmentidz.model_class

import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val age: Int,
    val name: String,
    val salary: Int
)