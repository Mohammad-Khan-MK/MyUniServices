package com.example.myuniservices.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myuniservices.auth.AuthHelper
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Snackbar helper
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Create Account", modifier = Modifier.padding(bottom = 20.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            val scope = rememberCoroutineScope()

            Button(
                onClick = {

                    scope.launch {

                        // 1. Check empty fields
                        if (fullName.isBlank() || email.isBlank() ||
                            password.isBlank() || confirmPassword.isBlank()
                        ) {
                            showMessage(snackbarHostState, "Please fill all fields")
                            return@launch
                        }

                        // 2. Check passwords match
                        if (password != confirmPassword) {
                            showMessage(snackbarHostState, "Passwords do not match")
                            return@launch
                        }

                        // 3. Register user using Firebase
                        AuthHelper.registerUser(
                            email = email,
                            password = password,
                            onSuccess = {
                                scope.launch {
                                    showMessage(snackbarHostState, "Registration successful!")
                                }
                                navController.navigate("login")
                            },
                            onError = { error ->
                                scope.launch {
                                    showMessage(snackbarHostState, error)
                                }
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Already have an account? Login here",
                modifier = Modifier.clickable {
                    navController.navigate("Login")
                }
            )
        }
    }
}

// Simple helper function
suspend fun showMessage(host: SnackbarHostState, message: String) {
    host.showSnackbar(message)
}
