package com.celestialinterface.nayaabb

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Authentication (val context: Activity){
    private val auth: FirebaseAuth = Firebase.auth
    private val login: Login = Login()
    private val register: Register = Register()

    fun register(username: String, password: String){
        auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful){
                    Toast.makeText(context, "success signing up", Toast.LENGTH_SHORT).show()
                    register.registered()
                }
                else{
                    Toast.makeText(context, "error signing up", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signin(username: String, password: String){
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful && it.isComplete){
                    Toast.makeText(context, "success logging in", Toast.LENGTH_SHORT).show()
                    login.loggedin()
                }
                else{
                    Toast.makeText(context, "failure logging in", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun isLoggedIn(): Boolean{
        if (auth.currentUser == null){
            return false
        }
        return true
    }

    fun out(){
        auth.signOut()
    }

    fun id(): String? {
        if (isLoggedIn()){
            return auth.currentUser?.uid
        }
        return null
    }

}