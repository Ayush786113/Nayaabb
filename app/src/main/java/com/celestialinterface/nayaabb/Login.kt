package com.celestialinterface.nayaabb

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {

    private lateinit var username_field: TextInputEditText
    private lateinit var password_field: TextInputEditText
    private lateinit var login_button: MaterialButton
    private lateinit var register_prompt: TextView
    private lateinit var authentication: Authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username_field = findViewById(R.id.login_username_edittext)
        password_field = findViewById(R.id.login_password_edittext)
        login_button = findViewById(R.id.login_button)
        register_prompt = findViewById(R.id.register_prompt)
        authentication = Authentication(this)

        login_button.setOnClickListener {
            var username = username_field.text.toString()
            var password = password_field.text.toString()
            if(username.isNotEmpty() && password.isNotEmpty()){
                authentication.signin(username, password)
            }
            else
                Toast.makeText(this, "Please fill up both the form fields", Toast.LENGTH_SHORT).show()
        }

        register_prompt.setOnClickListener{
            val intent: Intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }

    fun loggedin(){
        val intent: Intent = Intent(this, User::class.java)
        startActivity(intent)
    }
}