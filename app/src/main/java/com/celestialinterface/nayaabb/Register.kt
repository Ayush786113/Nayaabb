package com.celestialinterface.nayaabb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Register : AppCompatActivity() {

    private lateinit var username_edittext: TextInputEditText
    private lateinit var password_edittext: TextInputEditText
    private lateinit var register_button: MaterialButton
    private lateinit var authentication: Authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username_edittext = findViewById(R.id.register_username_edittext)
        password_edittext = findViewById(R.id.register_password_edittext)
        register_button = findViewById(R.id.register_button)
        authentication = Authentication(this)

        register_button.setOnClickListener {
            var username = username_edittext.text.toString()
            var password = password_edittext.text.toString()
            authentication.register(username, password)
        }

    }

    fun registered(){
        val intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}