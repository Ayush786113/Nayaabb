package com.celestialinterface.nayaabb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.util.*

class User : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var email_textview: TextView
    private lateinit var uid_textview: TextView
    private lateinit var options_list_view: ListView
    private lateinit var signout_btn: Button
    private lateinit var authentication: Authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        authentication = Authentication(this)
        email_textview = findViewById(R.id.email)
        uid_textview = findViewById(R.id.uid)
        signout_btn = findViewById(R.id.signout)
        options_list_view = findViewById(R.id.user_options_list_view)
        options_list_view.onItemClickListener = this
        signout_btn.setOnClickListener {
            authentication.out()
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, positiom: Int, p3: Long) {
        when(positiom){
            0 -> {
                Toast.makeText(this, "to be implemented", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                Toast.makeText(this, "to be implemented", Toast.LENGTH_SHORT).show()
            }
            2 -> {
//                Toast.makeText(this, "to be implemented", Toast.LENGTH_SHORT).show()
                about()
            }
        }
    }

    fun about(){
        val owner_dialogue: AlertDialog.Builder = AlertDialog.Builder(this)
        owner_dialogue.setView(layoutInflater.inflate(R.layout.about, null))
            .setCancelable(true)
        owner_dialogue.create().show()
    }
}