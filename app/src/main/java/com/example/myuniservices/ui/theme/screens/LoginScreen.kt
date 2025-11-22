package com.example.myuniservices.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myuniservices.auth.AuthHelper
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val keyboard = LocalSoftwareKeyboardController.current

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Welcome Back", Modifier.padding(bottom = 20.dp))

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
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    scope.launch {

                        // Hide keyboard befoere showing snackbar
                        keyboard?.hide()

                        if (email.isBlank() || password.isBlank()) {
                            snackbarHostState.showSnackbar("Please fill all fields")
                            return@launch
                        }

                        AuthHelper.loginUser(
                            email = email,
                            password = password,
                            onSuccess = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Login successful!")
                                }
                                navController.navigate("home")
                            },
                            onError = { message ->
                                scope.launch {
                                    snackbarHostState.showSnackbar(message)
                                }
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Don't have an account? Register",
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )
        }
    }
}
