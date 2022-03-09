package com.celestialinterface.nayaabb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var list_items: GridView
    private lateinit var authentication: Authentication
    private lateinit var fab: FloatingActionButton
    private lateinit var database: Database
    val databasefb = Firebase.database.reference
    var list: ArrayList<ResinProduct> = ArrayList()
    var keys: ArrayList<String> = ArrayList()
    var cart: ArrayList<String> = ArrayList()
    val context: Activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        analytics = Firebase.analytics
        list_items = findViewById(R.id.list_items)
        fab = findViewById(R.id.fab_account)
        authentication = Authentication(this)
        fab.setOnClickListener {
            if (authentication.isLoggedIn()){
                val intent: Intent = Intent(this, User::class.java)
                startActivity(intent)
            }
            else{
                val intent: Intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        list.clear()
        databasefb.child("products").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (product in snapshot.children){
                    var item: ResinProduct? = product.getValue(ResinProduct::class.java)
                    keys.add(product.key as String)
                    list.add(item as ResinProduct)
                }
                list = list.reversed() as ArrayList<ResinProduct>
                val array_adapter = ResinProductListAdapter(context, list)
                list_items.adapter = array_adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Database Error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        fab.setOnLongClickListener {
            val intent: Intent = Intent(this, Admin_Panel::class.java)
            startActivity(intent)
            true
        }
        list_items.onItemClickListener = this
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        cart.add(keys[position])
    }

//    override fun onStop() {
//        super.onStop()
//        authentication.id()?.let { database.cartupload(cart.toSet(), it) }
//    }

}