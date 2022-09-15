package com.example.jobreadiness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jobreadiness.predictorCategoryApi.CategoryPredictor
import com.example.jobreadiness.api.RetrofitClient
import com.example.jobreadiness.infra.JobReadinessConstants
import com.example.jobreadiness.infra.SecurityPreferences
import com.example.jobreadiness.databinding.ActivityTopProductsBinding
import com.example.jobreadiness.product.Products
import com.example.jobreadiness.topCategoryApi.TopCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopProducts : AppCompatActivity() {

    private lateinit var binding: ActivityTopProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val product = getProductName()
        getCategoryPredictorResponse("games")
        getTopCategoryResponse("MLB9190")
       // getProductsResponse("MLB1948115316")

    }

    private fun getCategoryPredictorResponse(product: String) {
        val categoryPredictorService = RetrofitClient.createService()
        val call: Call<List<CategoryPredictor>> = categoryPredictorService.listCategories(product)

        call.enqueue(object: Callback<List<CategoryPredictor>>{
            override fun onResponse (call: Call<List<CategoryPredictor>>, response: Response<List<CategoryPredictor>>) {
                val categories = response.body()
                    Log.d("Thiago", "test category $categories")
            }
            override fun onFailure(call: Call<List<CategoryPredictor>>, t: Throwable) {
                val s = ""
            }
        })
    }

    private fun getTopCategoryResponse(categoryId: String) {
        val topCategoryService = RetrofitClient.createService()
        val call: Call<TopCategory> = topCategoryService.listTopProducts(categoryId)

        call.enqueue(object: Callback<TopCategory>{
            override fun onResponse(call: Call<TopCategory>, response: Response<TopCategory>) {
                val topProducts = response.body()
                if(topProducts != null){
                    val itens = topProducts.content.filter { it.type == "ITEM" }.map { it.id }
                    Log.d("thiago", "test $itens")
                }
                else {
                    val s = ""
                }


            }

            override fun onFailure(call: Call<TopCategory>, t: Throwable) {
                val s = ""
            }

        })
    }

    private fun getProductsResponse(product: String) {
        val productService = RetrofitClient.createService()
        val call: Call<List<Products>> = productService.listItens(product)

        call.enqueue(object : Callback<List<Products>>{
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                val product = response.body()
                if(product != null){
                    Log.d("thiago", "test $product")
                }

            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                val s = ""
            }
        })
    }

    private fun getProductName(): String {
        return SecurityPreferences(this).getString(JobReadinessConstants.KEY.PRODUCT_SEARCHED)
    }

}