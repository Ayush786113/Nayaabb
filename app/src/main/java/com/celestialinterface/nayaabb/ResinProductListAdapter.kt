package com.celestialinterface.nayaabb

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ResinProductListAdapter (val context: Activity, val list: List<ResinProduct>): ArrayAdapter<ResinProduct>(context, R.layout.item_card, list) {
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_card, null)
        var title = view.findViewById<TextView>(R.id.resinname)
        var price = view.findViewById<TextView>(R.id.resinprice)
        var dp = view.findViewById<ImageView>(R.id.resindp)
        var product: ResinProduct = list.get(position)
        title.text = product.title
        price.text = "Rs. "+product.price
        Picasso.get().load(product.imageuri).into(dp)
        return view
    }
}