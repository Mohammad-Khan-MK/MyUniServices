package com.example.myuniservices.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // Top Row with title and profile icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "MyUniServices", style = MaterialTheme.typography.titleLarge)

            Text(
                text = "Profile",
                modifier = Modifier.clickable {
                    navController.navigate("profile")
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Choose a service below")

        Spacer(modifier = Modifier.height(24.dp))

        // Services list
        ServiceItem("Library")
        ServiceItem("IT Helpdesk")
        ServiceItem("Student Union")
        ServiceItem("Cafeteria")
    }
}

@Composable
fun ServiceItem(name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, modifier = Modifier.weight(1f))
            Text(text = ">")
        }
    }
}