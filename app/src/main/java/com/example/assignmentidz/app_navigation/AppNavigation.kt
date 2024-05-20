package com.example.assignmentidz.app_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.assignmentidz.model_class.Employee
import com.example.assignmentidz.ui.home_screen.EmployeeUiState
import com.example.assignmentidz.ui.home_screen.HomeScreen
import com.example.assignmentidz.ui.home_screen.HomeScreenViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    employeeUiState: EmployeeUiState,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen(
                employeeUiState = employeeUiState,
                employeeDetails = {
                    navController.navigate(
                        Employee(
                            name = it.name,
                            age = it.age,
                            salary = it.salary
                        )
                    )
                },
                tryAgain = {
                    homeScreenViewModel.getData()
                }
            )
        }

        composable<Employee> {
            val args = it.toRoute<Employee>()
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            text = "Employee Details", fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    navController.navigate(HomeScreen)
                                }
                            ) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back to Home Screen")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card{
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Name = ${args.name}",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Age = ${args.age}",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Salary = ${args.salary}",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}