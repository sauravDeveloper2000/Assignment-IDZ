package com.example.assignmentidz.ui.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentidz.model_class.Employee
import com.example.assignmentidz.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface EmployeeUiState{
    data object Loading: EmployeeUiState
    data class Success(val employees: List<Employee>): EmployeeUiState
    data object Error: EmployeeUiState
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
): ViewModel() {

    var employeeUiState: EmployeeUiState by mutableStateOf(EmployeeUiState.Loading)
        private set

    init {
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            employeeUiState = EmployeeUiState.Loading
            employeeUiState = try {
                val response = employeeRepository.getAllEmployees()
                EmployeeUiState.Success(employees = response.employees)
            } catch (e: IOException){
                EmployeeUiState.Error
            }
        }
    }
}