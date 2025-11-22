package com.example.myuniservices.auth


import com.google.firebase.auth.FirebaseAuth

object AuthHelper {

    fun registerUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Registration failed")
                }
            }
    }

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Login failed")
                }
            }
    }

}
