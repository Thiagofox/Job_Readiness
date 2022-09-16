package com.example.jobreadiness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobreadiness.ProductAdapter
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

        val searchProduct = getSearchProduct()



        getCategoryPredictorResponse(searchProduct){
            when (it) {
                is ApiResult.Error -> onListError(it.error)
                is ApiResult.Success -> onListLoaded(it.result)
            }
        }
    }

    fun onListError(error: Throwable) {
        // Toast
    }

    fun onListLoaded(list: List<Products>){
        initRecyclerView(list)
    }

    private fun getCategoryPredictorResponse(searchProduct: String, onResult: (ApiResult<List<Products>>) -> Unit) {
        val categoryPredictorService = RetrofitClient.createService()
        val call: Call<List<CategoryPredictor>> = categoryPredictorService.listCategories(searchProduct)

        call.enqueue(object : Callback<List<CategoryPredictor>> {
            override fun onResponse(call: Call<List<CategoryPredictor>>, response: Response<List<CategoryPredictor>>) {

                val categories = response.body()
                if (categories!= null){
                    getTopCategoryResponse(categories[0].categoryid, onResult)
                    Log.d("getCategoryPredictorResponse", "$categories")
                }else {
                    TODO("Not yet implements")
                }
            }

            override fun onFailure(call: Call<List<CategoryPredictor>>, t: Throwable) {
                val s = ""
            }
        })
    }

    private fun getTopCategoryResponse(categoryId: String, onResult: (ApiResult<List<Products>>) -> Unit) {
        val topCategoryService = RetrofitClient.createService()
        val call: Call<TopCategory> = topCategoryService.listTopProducts(categoryId)

        call.enqueue(object : Callback<TopCategory> {
            override fun onResponse(call: Call<TopCategory>, response: Response<TopCategory>) {
                val topProducts = response.body()
                if (topProducts != null) {
                    val items = topProducts.content.filter { it.type == "ITEM" }.map { it.id }
                    getProductsResponse(items, onResult)
                    Log.d("getTopCategoryResponse", "$items")
                } else {
                    TODO("Not yet implements")
                }
            }
            override fun onFailure(call: Call<TopCategory>, t: Throwable) {
                onResult(ApiResult.Error(t))
            }
        })
    }

    private fun getProductsResponse(productsIds: List<String>, onResult: (ApiResult<List<Products>>) -> Unit) {
        val productService = RetrofitClient.createService()
        val product = productsIds.joinToString(",")
        val call: Call<List<Products>> = productService.listItens(product)

        call.enqueue(object : Callback<List<Products>> {

            override fun onResponse( call: Call<List<Products>>, response: Response<List<Products>>) {
                val items: List<Products> = response.body() ?: emptyList()
                onResult(ApiResult.Success(items))
                Log.d("GetProductsResponse", "test $items")
                }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                onResult(ApiResult.Error(t))
            }
        })
    }

    private fun getSearchProduct(): String {
        return SecurityPreferences(this).getString(JobReadinessConstants.KEY.PRODUCT_SEARCHED)
    }


    private fun initRecyclerView(list: List<Products>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = ProductAdapter(list) { item ->
            val intent = Intent(this, ProductDetails::class.java )
            intent.putExtra("titlle", item.body.title)
            intent.putExtra("image", item.body.image)
            startActivity(intent)
        }
    }

}







