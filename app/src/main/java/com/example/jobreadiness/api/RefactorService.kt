package com.example.jobreadiness.api

import com.example.jobreadiness.predictorCategoryApi.CategoryPredictor
import com.example.jobreadiness.product.Products
import com.example.jobreadiness.topCategoryApi.TopCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val ACCESS_TOKEN: String = "APP_USR-2573149674415770-091605-68563d058674e5574e3c135e9eda3986-118766594"

interface RefactorService {
    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("sites/MLB/domain_discovery/search?limit=1")
    fun listCategories(@Query("q") searchProduct: String): Call<List<CategoryPredictor>>

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("highlights/MLB/category/{category}")
    fun listTopProducts(@Path(value = "category") topProducts: String): Call<TopCategory>

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("items")
    fun listItens(@Query("ids") products: List<String>): Call<List<Products>>

}

