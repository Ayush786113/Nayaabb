package com.celestialinterface.nayaabb

data class ResinProduct(var title: String? = null, var price: String? = null, var imageuri: String? = null) {
    override fun toString(): String {
        return "ResinProduct(title='$title', price='$price', imageURI=$imageuri)"
    }
}
