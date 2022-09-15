package com.example.jobreadiness.topCategoryApi

import com.google.gson.annotations.SerializedName

class TopCategory {
   @SerializedName("content")
   lateinit var content: List<TopCategoryItem>
}

class TopCategoryItem{
    @SerializedName("id")
    var id: String = ""

    @SerializedName("position")
    var position: String = ""

    @SerializedName("type")
    var type: String = ""
}