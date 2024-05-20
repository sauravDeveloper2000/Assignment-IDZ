package com.example.assignmentidz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignmentidz.app_navigation.AppNavigation
import com.example.assignmentidz.ui.home_screen.HomeScreen
import com.example.assignmentidz.ui.home_screen.HomeScreenViewModel
import com.example.assignmentidz.ui.theme.AssignmentIDZTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentIDZTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val employeeUiState = homeScreenViewModel.employeeUiState
                    AppNavigation(
                        employeeUiState = employeeUiState
                    )
                }
            }
        }
    }
}
