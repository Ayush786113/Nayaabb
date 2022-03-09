package com.celestialinterface.nayaabb

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.security.MessageDigest
import java.util.*
import kotlin.collections.ArrayList

class Database (var context: Activity) {
    val storageBucket = Firebase.storage.reference
    val database = Firebase.database.reference

    fun upload(name: String, price: String, imageURI: Uri){
        var path = storageBucket.child("images").child(Date().time.toString())
        path.putFile(imageURI)
            .addOnSuccessListener {
                path.downloadUrl.addOnSuccessListener {
                    productUpload(name, price, it, path)
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error! Contact the developer", Toast.LENGTH_SHORT).show()
            }
    }

    fun productUpload(name: String, price: String, imageURL: Uri?, imagepath: StorageReference){
        var product: ResinProduct = ResinProduct(name, price, imageURL.toString())
        database.child("products").push().setValue(product)
            .addOnSuccessListener {
                Toast.makeText(context, "Data uploaded successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "upload failed", Toast.LENGTH_SHORT).show()
                imagepath.delete()
                    .addOnFailureListener {
                        Toast.makeText(context, "redundant data exists", Toast.LENGTH_SHORT).show()
                    }
            }

    }

    fun cartupload(cart: Set<String>, id: String){
        database.child("cart").child(id).setValue(cart)
    }

}