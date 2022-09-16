package com.example.jobreadiness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jobreadiness.R
import com.example.jobreadiness.infra.SecurityPreferences
import com.example.jobreadiness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.searchButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.search_button) {
            handleSaveProduct()
        }
    }

    private fun handleSaveProduct() {
        val product = binding.editSearchProduct.text.toString()
        if(product != "") {

            val securityPreferences = SecurityPreferences(this)
            securityPreferences.storeString("PRODUCT_SEARCHED", product)

            val intent = Intent(this, TopProducts::class.java)
            startActivity(intent)

        }else {
            Toast.makeText(this, "Por Favor Digite Algo",Toast.LENGTH_LONG).show()
        }
    }

   /* private fun getProductName() {
        val product = SecurityPreferences(this).getString(JobReadinessConstants.KEY.USER_NAME)
        if(product != "") {
            Toast.makeText(this, product,Toast.LENGTH_LONG).show()
        }
    }*/

}