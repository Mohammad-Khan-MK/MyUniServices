package com.example.myuniservices.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(navController: NavController) {

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser


    var userName by remember { mutableStateOf("Loading...") }

    LaunchedEffect(Unit) {
        user?.let { currentUser ->

            val docRef = db.collection("users").document(currentUser.uid)


            docRef.get().addOnSuccessListener { snapshot ->

                if (snapshot.exists()) {
                    // Document exists → load name
                    userName = snapshot.getString("name") ?: "Unknown"
                } else {
                    // Document doesn't exist → save for the first time
                    val data = mapOf(
                        "name" to (currentUser.displayName ?: "No Name"),
                        "email" to (currentUser.email ?: "")
                    )

                    docRef.set(data)
                    userName = data["name"]!!
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Welcome, $userName",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Profile",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navController.navigate("profile") }
                    .padding(8.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Choose a service below")

        Spacer(modifier = Modifier.height(24.dp))

        ServiceItem("Services (Manage)") {
            navController.navigate("service")
        }

        ServiceItem("Library")
        ServiceItem("IT Helpdesk")
        ServiceItem("Student Union")
        ServiceItem("Cafeteria")
    }
}

@Composable
fun ServiceItem(name: String, onClick: (() -> Unit)? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick?.invoke() },
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, modifier = Modifier.weight(1f))
            Text(text = ">")
        }
    }
}
