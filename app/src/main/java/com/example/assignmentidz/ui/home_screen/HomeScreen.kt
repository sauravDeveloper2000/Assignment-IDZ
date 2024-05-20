package com.example.assignmentidz.ui.home_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.assignmentidz.components.ErrorComponent
import com.example.assignmentidz.components.LoadingComponent
import com.example.assignmentidz.model_class.Employee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    employeeUiState: EmployeeUiState,
    employeeDetails: (Employee) -> Unit,
    tryAgain: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Employee", fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge
                )
            })
        }
    ) { innerPadding ->
        when (employeeUiState) {
            EmployeeUiState.Error -> {
                ErrorComponent(
                    modifier = Modifier.fillMaxSize(),
                    retry = {
                        tryAgain()
                    }
                )
            }

            EmployeeUiState.Loading -> {
                LoadingComponent(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is EmployeeUiState.Success -> {
                OnSuccessCase(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    employees = employeeUiState.employees,
                    seeEmployeeDetails = {
                        employeeDetails(it)
                    }
                )
            }
        }
    }
}

@Composable
fun OnSuccessCase(
    modifier: Modifier,
    employees: List<Employee>,
    seeEmployeeDetails: (Employee) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp)
    ) {
        items(
            employees
        ) {
            SingleEmployee(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        seeEmployeeDetails(it)
                    },
                employee = it
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Composable
fun SingleEmployee(
    modifier: Modifier = Modifier,
    employee: Employee
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Name = ${employee.name}",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Age = ${employee.age}",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Salary = ${employee.salary}",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    Divider()
}