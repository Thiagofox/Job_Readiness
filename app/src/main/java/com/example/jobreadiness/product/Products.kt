package com.example.jobreadiness.product

import com.google.gson.annotations.SerializedName

class Products {

    @SerializedName("body")
    lateinit var body: ListProduct

}

class ListProduct {
    @SerializedName("id")
    var id: String = ""

    @SerializedName("title")
    var title: String = ""

    @SerializedName("category_id")
    var categoryId: String = ""

    @SerializedName("price")
    val price: String = ""


    

}