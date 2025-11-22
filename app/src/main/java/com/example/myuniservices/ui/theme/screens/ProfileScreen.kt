

package com.example.myuniservices.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavController) {

    val user = FirebaseAuth.getInstance().currentUser

    var fullName by remember { mutableStateOf(user?.displayName ?: "No name set") }
    var email by remember { mutableStateOf(user?.email ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "My Profile", modifier = Modifier.padding(bottom = 20.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = {},
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                FirebaseAuth.getInstance().signOut()
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}
































