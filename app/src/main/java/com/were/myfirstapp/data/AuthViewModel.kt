package com.were.myfirstapp.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.were.myfirstapp.model.User
import com.were.myfirstapp.navigation.ROUTE_DASHBOARD
import com.were.myfirstapp.navigation.ROUTE_LOGIN

class AuthViewModel : ViewModel() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signup(jina: String, email: String, pass: String, confrpass: String, context: Context, navController: NavController) {
        if (jina.isBlank() || email.isBlank() || pass.isBlank() || confrpass.isBlank()) {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_LONG).show()
            return
        } else if (pass != confrpass) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            return
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser!!.uid
                    val userdata = User(jina, email, pass, confrpass, userId)
                    val regRef = FirebaseDatabase.getInstance().getReference("Users").child(userId)
                    
                    regRef.setValue(userdata).addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            Toast.makeText(context, "Signup successful", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_DASHBOARD) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Database error: ${dbTask.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Signup failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun login(email: String, pass: String, context: Context, navController: NavController) {
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(context, "Please enter email and password", Toast.LENGTH_LONG).show()
            return
        }
        
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Successfully logged in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            } else {
                Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun logout(context: Context, navController: NavController) {
        mAuth.signOut()
        Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
        navController.navigate(ROUTE_LOGIN) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }
    
    fun isUserLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }
}