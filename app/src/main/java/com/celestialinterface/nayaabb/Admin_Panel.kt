package com.celestialinterface.nayaabb

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Admin_Panel : AppCompatActivity() {

    private lateinit var maindp: ImageView
    //    private lateinit var pic1: ImageView
//    private lateinit var pic2: ImageView
//    private lateinit var pic3: ImageView
    private lateinit var title : EditText
    private lateinit var price: EditText
    private lateinit var imageuri: Uri
    private val IMAGE_REQUEST_CODE: Int = 1
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        val upload = findViewById<Button>(R.id.uploadbtn_admin)
        val update = findViewById<Button>(R.id.updatebtn_admin)
        maindp = findViewById(R.id.resindp)
        title = findViewById(R.id.resintitle_admin)
        price = findViewById(R.id.resinprice)
        database = Database(this)
//        pic1 = findViewById(R.id.resinpic1)
//        pic2 = findViewById(R.id.resinpic2)
//        pic3 = findViewById(R.id.resinpic3)
        sharedPreferences = this.getSharedPreferences("RFY", MODE_PRIVATE)

        if (!sharedPreferences.getBoolean("pass_registered", false)){
            pass_register()
        }

        maindp.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }

        upload.setOnClickListener { pass_check() }
        update.setOnClickListener { Toast.makeText(this, "to be implemented", Toast.LENGTH_SHORT).show() }


    }

    fun upload_data( name: String, value: String , imageURI: Uri){
        database.upload(name, value, imageuri)
    }

    private fun pass_check() {
        val dialog = AlertDialog.Builder(this)
        val customView: View = layoutInflater.inflate(R.layout.pass_check, null)
        var passcode = customView.findViewById<EditText>(R.id.pass)
        dialog.setTitle("Authorize yourself").setMessage("Enter your secret code").setView(customView)
        dialog.setPositiveButton("Proceed"){ _, _ ->
            run {
                if (sharedPreferences.getString("passphrase", "").equals(passcode.text.toString())) {
                    upload_data(title.text.toString(), price.text.toString(), imageuri)
                }
                else{
                    Toast.makeText(this, "Passphrase mismatch", Toast.LENGTH_SHORT).show()
                }
            }
        }
        dialog.create().show()
    }

    private fun pass_register() {
        val dialog = AlertDialog.Builder(this)
        val customView: View = layoutInflater.inflate(R.layout.pass_check, null)
        dialog.setView(customView).setTitle("Enter a passphrase").setCancelable(false)
        dialog.setPositiveButton("Save"){_, _ ->
            run{
                var pass = customView.findViewById<EditText>(R.id.pass)
                with(sharedPreferences.edit()){
                    putString("passphrase", pass.text.toString())
                    putBoolean("pass_registered", true)
                    apply()
                }
                Toast.makeText(this , sharedPreferences.getString("passphrase", "Nothing Saved! Contact the Developer!"), Toast.LENGTH_SHORT).show()
            }
        }
        dialog.create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageuri = data?.data!!
            maindp.setImageURI(imageuri)
        }
    }
}