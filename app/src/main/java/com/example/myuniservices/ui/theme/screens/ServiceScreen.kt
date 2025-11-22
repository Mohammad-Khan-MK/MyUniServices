package com.example.myuniservices.ui.theme.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.myuniservices.data.database.AppDatabase
import com.example.myuniservices.data.entities.ServiceEntity
import com.example.myuniservices.data.repository.ServiceRepository
import com.example.myuniservices.viewmodel.ServiceViewModel
import com.example.myuniservices.viewmodel.ServiceViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ServiceScreen() {

    val context = LocalContext.current

    // Build database + repository + ViewModel
    val viewModel = createServiceViewModel(context)

    // Load services when screen opens
    LaunchedEffect(Unit) {
        viewModel.loadServices()
    }

    // For snackbar messages
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var serviceName by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Add a Service",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(12.dp))

            TextField(
                value = serviceName,
                onValueChange = { serviceName = it },
                label = { Text("Service Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {

                    if (serviceName.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Name cannot be empty")
                        }
                        return@Button
                    }

                    viewModel.addService(
                        ServiceEntity(name = serviceName)
                    )
                    serviceName = ""

                    scope.launch {
                        snackbarHostState.showSnackbar("Service saved!")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Service")
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Saved Services",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(10.dp))

            LazyColumn {
                items(viewModel.services) { service ->
                    Text(
                        text = service.name,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun createServiceViewModel(context: Context): ServiceViewModel {
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "myuniservices.db"
    ).build()

    val repo = ServiceRepository(db.serviceDao())

    return viewModel(
        factory = ServiceViewModelFactory(repo)
    )
}
